import React, { useState, Fragment, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import Input from "../controll/input";
import Select from "../controll/select";
import { useFormik, yupToFormError, Field } from "formik";
import * as Yup from "yup";
import staffService from "./../services/staffService";
import violationService from "./../services/violationService";
import timeKeepingService from "./../services/timeKeepingService";

import MUIDataTable from "mui-datatables";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import { Delete } from "@material-ui/icons";

const TimeKeeping = (props) => {
  const selectTK = [
    { value: "Có", label: "Có" },
    { value: "Ốm", label: "Ốm" },
    { value: "Con_ốm", label: "Con ốm" },
    { value: "Thai_sản", label: "Thai sản" },
    { value: "Tai_nạn", label: "Tai nạn" },
    { value: "Chủ_nhật", label: "Chủ nhật" },
    { value: "Nghĩ_lễ", label: "Nghĩ lễ" },
    { value: "Nghĩ_bù", label: "Nghĩ bù" },
    { value: "Nghĩ_nữa_ngày_không_lương", label: "Nghĩ nữa ngày không lương" },
    { value: "Nghĩ_không_lương", label: "Nghĩ không lương" },
    { value: "Ngừng_việc", label: "Ngừng việc" },
    { value: "Nghĩ_phép", label: "Nghĩ phép" },
    { value: "Nghĩ_nữa_ngày_tính_phép", label: "Nghĩ nữa ngày tính phép" },
    { value: "Làm_nữa_ngày_công", label: "Làm nữa ngày công" },
  ];
  const [staffs, setStaffs] = useState([]);
  const [staffsId, setStaffsId] = useState(0);
  const [modalShow, setModalShow] = useState(false);
  const [tks, setTks] = useState([]);
  const [tksId, setTksId] = useState(0);
  const [vios, setVios] = useState([]);
  const [violationId, setviolationId] = useState(0);
  const [selectedOption, setSelectedOption] = useState(0);
  const [totalItem, setToltalItem] = useState(0);

  const formik = useFormik({
    initialValues: {
      tkStatus: "",
      staffId: 0,
    },
    validationSchema: Yup.object({
      tkStatus: Yup.string().required("Require"),
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
    setTksId(dataId);
    if (dataId != null) {
      timeKeepingService.getById(dataId).then((res) => {
        var tk = res;
        const result = [];
        result.push({
          tkId: tk.timeKeeping.tkId,
          tkDay: tk.timeKeeping.tkDay,
          tkStatus: tk.timeKeeping.tkStatus,
          staffId: tk.staffId,
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
    timeKeepingService.remove(dataId).then((res) => {
      // if (res.errorCode === 0) {
      loadData();
      // } else {
      // }
    });
  };

  const handleFormSubmit = (data) => {
    const result = [];

    result.push({
      // tkDay: data.tkDay,
      tkStatus: data.tkStatus,
    });
    if (tksId == null) {
      console.log(result[0]);
      timeKeepingService.add(result[0], data.staffId).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    } else {
      console.log(result);
      timeKeepingService.update(tksId, result[0], data.staffId).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    }
  };

  const loadData = () => {
    timeKeepingService.getAll().then((res) => {
      // if(res.errorCode === 0) {
      setTks(res.timeKeeping);
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
  };

  useEffect(() => {
    loadData();
  }, []);

  const columns = [
    {
      name: "tkId",
      label: "Id điểm danh",
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
      name: "tkDay",
      label: "Ngày",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "tkStatus",
      label: "Trạng thái",
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
                <h3 className="card-title">Điểm danh</h3>
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
                data={tks}
                columns={columns}
                options={options}
              />
              {/* <table className="table table-bordered table-hover table-striped">
                <thead>
                  <tr className="table-primary">
                    <th style={{ width: "50px" }}>{totalItem}</th>
                    <th>Ngày</th>
                    <th>Trạng thái</th>
                    <th style={{ width: "80px" }} />
                  </tr>
                </thead>
                <tbody>
                  {tks.map((tk, idx) => {
                    return (
                      <tr key={tk.tkId}>
                        <td>
                          {idx + 1}/{totalItem}
                        </td>
                        <td>{tk.tkDay}</td>
                        <td>{tk.tkStatus}</td>
                        <td>
                          <a
                            href="/#"
                            onClick={(e) => handleModalShow(e, tk.tkId)}
                          >
                            <i className="fas fa-edit text-primary" />
                          </a>
                          <a href="/#" onClick={(e) => deleteRow(e, tk.tkId)}>
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
          <Modal.Title>Điểm danh</Modal.Title>
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
            <Input
              id="txtTkDay"
              type="text"
              disabled
              label="Ngày"
              maxLength="100"
              frmField={formik.getFieldProps("tkDay")}
              err={formik.touched.tkDay && formik.errors.tkDay}
              errMessage={formik.errors.tkDay}
            ></Input>
            <div className="row">
              <div className="col-sm-3 col-form-label">
                <p>Trạng thái</p>
              </div>
              <div className="col-sm-8 ml-3">
                <Select
                  className="select w-100"
                  onChange={(value) =>
                    formik.setFieldValue("tkStatus", value.value)
                  }
                  value={formik.values.value}
                  options={selectTK}
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

export default TimeKeeping;
