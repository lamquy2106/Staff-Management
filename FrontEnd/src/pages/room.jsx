import React, { useState, Fragment, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import Input from "../controll/input";
import { useFormik, yupToFormErrors, withFormik } from "formik";
import * as Yup from "yup";
import roomService from "./../services/roomService";

import MUIDataTable from "mui-datatables";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import { Delete } from "@material-ui/icons";
const Room = (props) => {
  const [rooms, setRooms] = useState([]);
  const [roomsId, setRoomsId] = useState(0);
  const [modalShow, setModalShow] = useState(false);
  const [totalItem, setToltalItem] = useState(0);

  const formik = useFormik({
    initialValues: {
      name: "",
    },
    validationSchema: Yup.object({
      name: Yup.string().required("Require"),
    }),
    onSubmit: (values) => {
      handleFormSubmit(values);
    },
  });

  const handleModalClose = () => {
    setModalShow(false);
  };
  const handleModalShow = (dataId) => {
    // if (e) e.preventDefault();
    // console.log(dataId);
    setRoomsId(dataId);
    if (dataId != null) {
      roomService.getById(dataId).then((res) => {
        // console.log(res);
        formik.setValues(res);
        setModalShow(true);
      });
    } else {
      formik.resetForm();
      setModalShow(true);
    }
  };

  const deleteRow = (dataId) => {
    // e.preventDefault();
    roomService.remove(dataId).then((res) => {
      // if (res.errorCode === 0) {
      loadData();
      // } else {
      // }
    });
  };

  const handleFormSubmit = (data) => {
    if (roomsId == null) {
      roomService.add(data).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    } else {
      roomService.update(roomsId, data).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    }
  };

  const loadData = () => {
    roomService.getAll().then((res) => {
      // if(res.errorCode === 0) {
      setRooms(res.room);
      setToltalItem(res.totalItems);
      // }
    });
  };

  useEffect(() => {
    loadData();
  }, []);

  const columns = [
    {
      name: "roomId",
      label: "Id phòng",
      options: {
        display: false,
      },
    },
    {
      name: "stt",
      label: "STT",
      options: {
        filter: true,
        sort: false,
        customBodyRender: (value, tableMeta, updateValue) => {
          return <p>{tableMeta.rowIndex + 1}</p>;
          // }
        },
      },
    },

    {
      name: "name",
      label: "Tên phòng",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "edit",
      label: " ",
      options: {
        filter: true,
        sort: false,

        customBodyRender: (value, tableMeta, updateValue) => {
          return (
            <button
              className="btn btn-primary"
              onClick={() => {
                handleModalShow(tableMeta.rowData[0]);
              }}
            >
              Sửa
            </button>
          );
        },
      },
    },
    {
      name: "delete",
      label: " ",
      options: {
        filter: true,
        sort: false,

        customBodyRender: (value, tableMeta, updateValue) => {
          return (
            <button
              className="btn btn-danger"
              onClick={() => {
                deleteRow(tableMeta.rowData[0]);
              }}
            >
              Xoá
            </button>
          );
        },
      },
    },
  ];

  const options = {
    filter: true,
    filterType: "dropdown",
    selectableRows: false,
    onDownload: (buildHead, buildBody, columns, data) => {
      return "\uFEFF" + buildHead(columns) + buildBody(data);
    },
    // onRowClick: (rowData) => {
    // handleModalShow(rowData[0]);
    // console.log(rowData);
    // },
  };

  return (
    <Fragment>
      <div className="container mt-4">
        <div className="card border-primary bt-primary-5">
          <div className="card-header">
            <div className="row">
              <div className="col">
                <h3 className="card-title">Phòng ban</h3>
              </div>
              <div className="col-auto">
                <button
                  type="button"
                  className="btn btn-primary"
                  data-toggle="modal"
                  onClick={() => handleModalShow(null, 0)}
                >
                  <i className="fas fa-plus" /> Thêm
                </button>
              </div>
            </div>
          </div>
          <div className="card-body">
            <div className="table-responsive">
              <MUIDataTable
                title={""}
                data={rooms}
                columns={columns}
                options={options}
              />
              {/* <table className="table table-bordered table-hover table-striped">
                <thead>
                  <tr className="table-primary">
                    <th style={{ width: "50px" }}>{totalItem}</th>
                    <th>Tên trạng thái</th>
                    <th style={{ width: "80px" }} />
                  </tr>
                </thead>
                <tbody>
                  {rooms.map((room, idx) => {
                    return (
                      <tr key={room.roomId}>
                        <td>
                          {idx + 1}/{totalItem}
                        </td>
                        <td>{room.name}</td>
                        <td>
                          <a
                            href="/#"
                            onClick={(e) => handleModalShow(e, room.roomId)}
                          >
                            <i className="fas fa-edit text-primary" />
                          </a>
                          <a
                            href="/#"
                            onClick={(e) => deleteRow(e, room.roomId)}
                          >
                            <i className="fas fa-trash-alt text-danger ml-2" />
                          </a>
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table> */}
            </div>
          </div>
        </div>
      </div>

      <Modal
        show={modalShow}
        onHide={handleModalClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Phòng ban</Modal.Title>
        </Modal.Header>
        <form onSubmit={formik.handleSubmit}>
          <Modal.Body>
            <Input
              id="txtRoom"
              type="text"
              label="Tên phòng"
              maxLength="100"
              frmField={formik.getFieldProps("name")}
              err={formik.touched.name && formik.errors.name}
              errMessage={formik.errors.name}
            ></Input>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleModalClose}>
              Close
            </Button>
            <Button
              variant="primary"
              type="submit"
              // disabled={!(formik.dirty && formik.isValid)}
            >
              Save
            </Button>
          </Modal.Footer>
        </form>
      </Modal>
    </Fragment>
  );
};

export default Room;
