import React, { useState, Fragment, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import Input from "../controll/input";
import Select from "../controll/select";
import { useFormik, yupToFormError, Field } from "formik";
import * as Yup from "yup";
import roomService from "./../services/roomService";
import roomProjectService from "./../services/roomProjectService";
import projectService from "./../services/projectService";

import MUIDataTable from "mui-datatables";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import { Delete } from "@material-ui/icons";

const RoomProject = (props) => {
  const selectStatus = [
    { value: "Hoàn_thành", label: "Hoàn thành" },
    { value: "Chưa_hoàn_thành", label: "Chưa hoàn thành" },
  ];
  const [pros, setPros] = useState([]);
  const [prosId, setProsId] = useState(0);
  const [modalShow, setModalShow] = useState(false);
  const [rps, setRps] = useState([]);
  const [rpsId, setRpsId] = useState(0);
  const [rooms, setRooms] = useState([]);
  const [roomId, setRoomId] = useState(0);
  const [selectedOption, setSelectedOption] = useState(0);
  const [totalItem, setToltalItem] = useState(0);

  const formik = useFormik({
    initialValues: {
      status: "",
      receivedDate: "",
      finishDate: "",
      roomId: 0,
      proId: 0,
    },
    validationSchema: Yup.object({
      receivedDate: Yup.string().required("Require"),
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
    setRpsId(dataId);
    if (dataId != null) {
      roomProjectService.getById(dataId).then((res) => {
        var rp = res;
        const result = [];
        result.push({
          rp: rp.roomProject.rpId,
          status: rp.roomProject.status,
          receivedDate: rp.roomProject.receivedDate,
          finishDate: rp.roomProject.finishDate,
          proId: rp.proId,
          roomId: rp.roomId,
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
    roomProjectService.remove(dataId).then((res) => {
      // if (res.errorCode === 0) {
      loadData();
      // } else {
      // }
    });
  };

  const handleFormSubmit = (data) => {
    const result = [];

    result.push({
      status: data.status,
      receivedDate: data.receivedDate,
      finishDate: data.finishDate,
    });
    if (rpsId == null) {
      console.log(result[0]);
      roomProjectService.add(result[0], data.proId, data.roomId).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    } else {
      console.log(result);
      roomProjectService
        .update(rpsId, result[0], data.proId, data.roomId)
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
    roomProjectService.getAll().then((res) => {
      // if(res.errorCode === 0) {
      setRps(res.roomProject);
      setToltalItem(res.totalItems);
      //console.log(res.content[0])   ;
      // }
    });
    projectService.getAll().then((res) => {
      var project = res.project;
      var totalProject = project.length;
      const options = [];
      for (var i = 0; i < totalProject; i++) {
        //console.log(area[i]);
        options.push({
          value: project[i].proId,
          label: project[i].proName,
        });
      }
      console.log(options);
      // if(res.errorCode === 0) {
      setPros(options);

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
      name: "rpId",
      label: "Id phòng dự án",
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
      name: "status",
      label: "Trạng thái",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "receivedDate",
      label: "Ngày nhận",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "finishDate",
      label: "Ngày hoàn thành",
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
                <h3 className="card-title">Tiếp nhận dự án</h3>
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
                data={rps}
                columns={columns}
                options={options}
              />
              {/* <table className="table table-bordered table-hover table-striped">
                <thead>
                  <tr className="table-primary">
                    <th style={{ width: "50px" }}>{totalItem}</th>
                    <th>Ngày nhận</th>
                    <th>trạng thái</th>
                    <th>Ngày hoàn thành</th>
                    <th style={{ width: "80px" }} />
                  </tr>
                </thead>
                <tbody>
                  {rps.map((rp, idx) => {
                    return (
                      <tr key={rp.rpId}>
                        <td>
                          {idx + 1}/{totalItem}
                        </td>
                        <td>{rp.receivedDate}</td>
                        <td>{rp.status}</td>
                        <td>{rp.finishDate}</td>

                        <td>
                          <a
                            href="/#"
                            onClick={(e) => handleModalShow(e, rp.rpId)}
                          >
                            <i className="fas fa-edit text-primary" />
                          </a>
                          <a href="/#" onClick={(e) => deleteRow(e, rp.rpId)}>
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
          <Modal.Title>Tiếp nhận dự án</Modal.Title>
        </Modal.Header>
        <form onSubmit={formik.handleSubmit}>
          <Modal.Body>
            <div className="row">
              <div className="col-sm-3 col-form-label">
                <p>dự án</p>
              </div>
              <div className="col-sm-8 ml-3">
                <Select
                  className="select w-100"
                  onChange={(value) =>
                    formik.setFieldValue("proId", value.value)
                  }
                  value={formik.values.proId}
                  options={pros}
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
            <Input
              id="txtReceivedDate"
              type="text"
              label="Ngày nhận"
              maxLength="100"
              frmField={formik.getFieldProps("receivedDate")}
              err={formik.touched.receivedDate && formik.errors.receivedDate}
              errMessage={formik.errors.receivedDate}
            ></Input>
            <div className="row">
              <div className="col-sm-3 col-form-label">
                <p>Trạng thái</p>
              </div>
              <div className="col-sm-8 ml-3">
                <Select
                  className="select w-100"
                  onChange={(value) =>
                    formik.setFieldValue("status", value.value)
                  }
                  value={formik.values.value}
                  options={selectStatus}
                />
              </div>
            </div>
            <Input
              id="txtFinishDate"
              type="text"
              label="Ngày hoàn thành"
              maxLength="100"
              frmField={formik.getFieldProps("finishDate")}
              err={formik.touched.finishDate && formik.errors.finishDate}
              errMessage={formik.errors.finishDate}
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

export default RoomProject;
