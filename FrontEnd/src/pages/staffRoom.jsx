import React, { useState, Fragment, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import Input from "../controll/input";
import Select from "../controll/select";
import { useFormik, yupToFormError, Field } from "formik";
import * as Yup from "yup";
import staffService from "./../services/staffService";
import roomService from "./../services/roomService";
import staffRoomService from "../services/staffRoomService";

import MUIDataTable from "mui-datatables";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import { Delete } from "@material-ui/icons";

const StaffRoom = (props) => {
  const selectPosition = [
    { value: "Nhân_viên", label: "Nhân viên" },
    { value: "Quản_lý", label: "Quản lý" },
  ];
  const [staffs, setStaffs] = useState([]);
  const [staffsId, setStaffsId] = useState(0);
  const [modalShow, setModalShow] = useState(false);
  const [srs, setSrs] = useState([]);
  const [srsId, setSrsId] = useState(0);
  const [rooms, setRooms] = useState([]);
  const [roomId, setRoomId] = useState(0);
  const [selectedOption, setSelectedOption] = useState(0);
  const [totalItem, setToltalItem] = useState(0);

  const formik = useFormik({
    initialValues: {
      position: "",
      roomId: 0,
      staffId: 0,
    },
    onSubmit: (values) => {
      handleFormSubmit(values);
    },
  });

  const handleModalClose = () => {
    setModalShow(false);
  };
  const handleModalShow = (dataId) => {
    // if (e) e.preventDefault();
    setSrsId(dataId);
    if (dataId != null) {
      staffRoomService.getById(dataId).then((res) => {
        var sr = res;
        const result = [];
        result.push({
          sr: sr.staffRoom.srId,
          position: sr.staffRoom.position,
          staffId: sr.staffId,
          roomId: sr.roomId,
        });
        formik.setValues(result[0]);
        setModalShow(true);
      });
    } else {
      formik.resetForm();
      setModalShow(true);
    }
  };

  const deleteRow = (dataId) => {
    // e.preventDefault();
    staffRoomService.remove(dataId).then((res) => {
      // if (res.errorCode === 0) {
      loadData();
      // } else {
      // }
    });
  };

  const handleFormSubmit = (data) => {
    const result = [];

    result.push({
      position: data.position,
    });
    if (srsId == null) {
      console.log(result[0]);
      staffRoomService.add(result[0], data.staffId, data.roomId).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    } else {
      console.log(result);
      staffRoomService
        .update(srsId, result[0], data.staffId, data.roomId)
        .then((res) => {
          // if (res.errorCode === 0) {
          loadData();
          handleModalClose();
          // } else {
          // }
        });
    }
  };

  const loadData = () => {
    staffRoomService.getAll().then((res) => {
      // if(res.errorCode === 0) {
      setSrs(res.staffRoom);
      setToltalItem(res.totalItems);
      //console.log(res.content[0])   ;
      // }
    });
    staffService.getAll().then((res) => {
      var staff = res.staff;
      var totalStaff = staff.length;
      const options = [];
      for (var i = 0; i < totalStaff; i++) {
        //console.log(area[i]);
        options.push({
          value: staff[i].staffId,
          label: staff[i].staffFirstName + " " + staff[i].staffLastName,
        });
      }
      console.log(options);
      // if(res.errorCode === 0) {
      setStaffs(options);

      //console.log(res.content[0])   ;
      // }
    });
    roomService.getAll().then((res) => {
      var room = res.room;
      var totalRoom = room.length;
      const options = [];
      for (var i = 0; i < totalRoom; i++) {
        //console.log(area[i]);
        options.push({
          value: room[i].roomId,
          label: room[i].name,
        });
      }
      console.log(options);
      // if(res.errorCode === 0) {
      setRooms(options);

      //console.log(res.content[0])   ;
      // }
    });
  };

  useEffect(() => {
    loadData();
  }, []);

  const columns = [
    {
      name: "srId",
      label: "Id nhân viên-phòng",
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
      name: "position",
      label: "Chức vụ",
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
                <h3 className="card-title">Nhân viên - Phòng</h3>
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
                data={srs}
                columns={columns}
                options={options}
              />
              {/* <table className="table table-bordered table-hover table-striped">
                <thead>
                  <tr className="table-primary">
                    <th style={{ width: "50px" }}>{totalItem}</th>
                    <th>Chức vụ</th>
                    <th style={{ width: "80px" }} />
                  </tr>
                </thead>
                <tbody>
                  {srs.map((sr, idx) => {
                    return (
                      <tr key={sr.srId}>
                        <td>
                          {idx + 1}/{totalItem}
                        </td>
                        <td>{sr.position}</td>
                        <td>
                          <a
                            href="/#"
                            onClick={(e) => handleModalShow(e, sr.srId)}
                          >
                            <i className="fas fa-edit text-primary" />
                          </a>
                          <a href="/#" onClick={(e) => deleteRow(e, sr.srId)}>
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
          <Modal.Title>Nhân viên - phòng</Modal.Title>
        </Modal.Header>
        <form onSubmit={formik.handleSubmit}>
          <Modal.Body>
            <div className="row">
              <div className="col-sm-3 col-form-label">
                <p>Nhân viên</p>
              </div>
              <div className="col-sm-8 ml-3">
                <Select
                  className="select w-100"
                  onChange={(value) =>
                    formik.setFieldValue("staffId", value.value)
                  }
                  value={formik.values.staffId}
                  options={staffs}
                />
              </div>
            </div>
            <div className="row">
              <div className="col-sm-3 col-form-label">
                <p>Phòng ban</p>
              </div>
              <div className="col-sm-8 ml-3">
                <Select
                  className="select w-100"
                  onChange={(value) =>
                    formik.setFieldValue("roomId", value.value)
                  }
                  value={formik.values.roomId}
                  options={rooms}
                />
              </div>
            </div>
            <div className="row">
              <div className="col-sm-3 col-form-label">
                <p>Chức vụ</p>
              </div>
              <div className="col-sm-8 ml-3">
                <Select
                  className="select w-100"
                  onChange={(value) =>
                    formik.setFieldValue("position", value.value)
                  }
                  value={formik.values.value}
                  options={selectPosition}
                />
              </div>
            </div>
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

export default StaffRoom;
