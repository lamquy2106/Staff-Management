import React, { useState, Fragment, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import Input from "../controll/input";
import Select from "../controll/select";
import { useFormik, yupToFormError, Field } from "formik";
import * as Yup from "yup";
import staffStatusService from "./../services/staffStatusService";
import staffService from "./../services/staffService";

import MUIDataTable from "mui-datatables";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import { Delete } from "@material-ui/icons";
import sendEmailService from "./../services/sendEmailService";

const Staff = (props) => {
  const selectGender = [
    { value: "Nam", label: "Nam" },
    { value: "Nữ", label: "Nữ" },
  ];
  const selectRole = [
    { value: "admin", label: "Admin" },
    { value: "manager", label: "Quản lý" },
    { value: "staff", label: "Nhân viên" },
  ];
  const selectSS = [
    { value: "Đang_làm_việc", label: "Đang làm việc" },
    { value: "Nghĩ_việc", label: "Nghĩ việc" },
  ];
  const [staffs, setStaffs] = useState([]);
  const [staffsId, setStaffsId] = useState(0);
  const [modalShow, setModalShow] = useState(false);
  const [modalShowEmail, setModalShowEmail] = useState(false);
  const [selectedOption, setSelectedOption] = useState(0);
  const [totalItem, setToltalItem] = useState(0);

  const formik = useFormik({
    initialValues: {
      staffCode: "",
      staffFirstName: "",
      staffLastName: "",
      birthDay: "",
      staffPhone: "",
      staffAddress: "",
      staffCMND: "",
      staffStarDay: "",
      staffStatus: "",
      userName: "",
      staffEmail: "",
      password: "",
      role: "",
    },
    validationSchema: Yup.object({
      staffCode: Yup.string().required("Require"),
      staffFirstName: Yup.string().required("Require"),
      staffLastName: Yup.string().required("Require"),
    }),
    onSubmit: (values) => {
      handleFormSubmit(values);
    },
  });

  const formikEmail = useFormik({
    initialValues: {
      subject: "",
      body: "",
      image: "",
    },
    validationSchema: Yup.object({
      subject: Yup.string().required("Require"),
      body: Yup.string().required("Require"),
    }),
    onSubmit: (values) => {
      handleFormSubmitEmail(values);
    },
  });

  const handleModalClose = () => {
    setModalShow(false);
    setModalShowEmail(false);
  };
  const handleModalShow = (dataId) => {
    // if (e) e.preventDefault();
    setStaffsId(dataId);
    if (dataId != null) {
      staffService.getById(dataId).then((res) => {
        formik.setValues(res);
        setModalShow(true);
        // var staff = res;
        // const result = [];
        // console.log(staff.staffId);
        // result.push({
        //   staffId: staff.staff.staffId,
        //   staffCode: staff.staff.staffCode,
        //   staffFirstName: staff.staff.staffFirstName,
        //   staffLastName: staff.staff.staffLastName,
        //   gender: staff.staff.gender,
        //   staffPhone: staff.staff.staffPhone,
        //   staffAddress: staff.staff.staffAddress,
        //   staffEmail: staff.staff.staffEmail,
        //   staffCMND: staff.staff.staffCMND,
        //   staffStarDay: staff.staff.staffStarDay,
        //   staffStatus: staff.staff.staffStatus,
        //   role: staff.staff.role,
        //   userName: staff.staff.userName,
        //   password: staff.staff.password,
        // });
        // console.log(staff.staff.staffId);
        // formik.setValues(result[0]);
        // setModalShow(true);
      });
    } else {
      formik.resetForm();
      setModalShow(true);
    }
  };

  const handleModalShowEmail = (dataId) => {
    // if (e) e.preventDefault();
    setStaffsId(dataId);
    console.log(staffsId);
    formik.resetForm();
    setModalShowEmail(true);
  };

  const handleFormSubmitEmail = (data) => {
    const result = [];
    const temp = new String(data.image);
    const spliceImage = () => {
      return temp.slice(12);
    };
    result.push({
      subject: data.subject,
      body: data.body,
      image: "D:\\dulieu\\" + spliceImage(),
    });
    console.log(result[0]);
    staffService.sendEmail(staffsId, result[0]).then((res) => {
      // if (res.errorCode === 0) {
      // loadData();
      handleModalClose();
      // } else {
      // }
    });
  };

  const deleteRow = (dataId) => {
    // e.preventDefault();
    staffService.remove(dataId).then((res) => {
      // if (res.errorCode === 0) {
      loadData();
      // } else {
      // }
    });
  };

  const handleFormSubmit = (data) => {
    const result = [];
    const roles = [data.roles];
    console.log(roles);
    // if (data.roles. == "admin") {
    //   roles = ["admin"];
    // } else if (data.roles == "manager") {
    //   roles = ["manager"];
    // } else {
    //   roles = ["staff"];
    // }
    result.push({
      // staffId: data.staffId,
      staffCode: data.staffCode,
      staffFirstName: data.staffFirstName,
      staffLastName: data.staffLastName,
      gender: data.gender,
      birthDay: data.birthDay,
      staffPhone: data.staffPhone,
      staffAddress: data.staffAddress,
      staffCMND: data.staffCMND,
      staffStarDay: data.staffStarDay,
      staffStatus: data.staffStatus,
      userName: data.userName,
      staffEmail: data.staffEmail,
      password: data.password,
      role: roles,
    });
    // console.log("jhsdgfg");
    if (staffsId == null) {
      console.log(result[0]);
      staffService.add(result[0]).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    } else {
      console.log(result);
      staffService.update(staffsId, result[0]).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    }
  };

  const loadData = () => {
    staffService.getAll().then((res) => {
      // if(res.errorCode === 0) {
      setStaffs(res.staff);
      setToltalItem(res.totalItems);
      // console.log(res);
      // }
    });
    // staffStatusService.getAll().then((res) => {
    //   var staffStatus = res.staffStatus;
    //   var totalSS = staffStatus.length;
    //   const options = [];
    //   for (var i = 0; i < totalSS; i++) {
    //     //console.log(area[i]);
    //     options.push({
    //       value: staffStatus[i].staffStatusId,
    //       label: staffStatus[i].name,
    //     });
    //   }
    //   console.log(options);
    //   // if(res.errorCode === 0) {
    //   setStaffStatuss(options);

    //   //console.log(res.content[0])   ;
    //   // }
    // });
  };

  useEffect(() => {
    loadData();
  }, []);

  const columns = [
    {
      name: "staffId",
      label: "Id Nhân viên",
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
      name: "staffCode",
      label: "Mã nhân viên",
      options: {
        filter: true,
        display: false,
        sort: true,
      },
    },
    {
      name: "staffFirstName",
      label: "Họ",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "staffLastName",
      label: "Tên",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "gender",
      label: "Giới tính",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "birthDay",
      label: "Ngày sinh",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "staffPhone",
      label: "Điện thoại",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "staffAddress",
      label: "Địa chỉ",
      options: {
        filter: true,
        sort: true,
        display: false,
      },
    },
    {
      name: "staffEmail",
      label: "Email",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "staffCMND",
      label: "CMND",
      options: {
        filter: true,
        display: false,
        sort: true,
      },
    },
    {
      name: "staffStarDay",
      label: "Ngày vào làm",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "staffStatus",
      label: "Trạng thái",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "userName",
      label: "tài khoản",
      options: {
        filter: true,
        display: false,
        sort: true,
      },
    },
    {
      name: "password",
      label: "Mật khẩu",
      options: {
        filter: true,
        display: false,
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
    {
      name: "sendEmail",
      label: " ",
      options: {
        filter: true,
        sort: false,

        customBodyRender: (value, tableMeta, updateValue) => {
          return (
            <button
              className="btn btn-success"
              onClick={() => {
                handleModalShowEmail(tableMeta.rowData[0]);
              }}
            >
              Gữi mail
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
                <h3 className="card-title">Nhân viên</h3>
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
                data={staffs}
                columns={columns}
                options={options}
              />
              {/* <table className="table table-bordered table-hover table-striped">
                <thead>
                  <tr className="table-primary">
                    <th style={{ width: "50px" }}>{totalItem}</th>
                    <th>Mã nhân viên</th>
                    <th>Họ</th>
                    <th>Tên</th>
                    <th>Giới tình</th>
                    <th>Điện thoại</th>
                    <th>Địa chỉ</th>
                    <th>Email</th>
                    <th>CMND</th>
                    <th>Ngày vào làm</th>
                    <th>Chức vụ</th>
                    <th>Trạng thái</th>
                    <th style={{ width: "80px" }} />
                  </tr>
                </thead>
                <tbody>
                  {staffs.map((staff, idx) => {
                    return (
                      <tr key={staff.staffId}>
                        <td>
                          {idx + 1}/{totalItem}
                        </td>
                        <td>{staff.staffCode}</td>
                        <td>{staff.staffFirstName}</td>
                        <td>{staff.staffLastName}</td>
                        <td>{staff.gender}</td>
                        <td>{staff.staffPhone}</td>
                        <td>{staff.staffAddress}</td>
                        <td>{staff.staffEmail}</td>
                        <td>{staff.staffCMND}</td>
                        <td>{staff.staffStarDay}</td>
                        <td>{staff.role}</td>
                        <td>{staff.staffStatus}</td>
                        <td>
                          <a
                            href="/#"
                            onClick={(e) => handleModalShow(e, staff.staffId)}
                          >
                            <i className="fas fa-edit text-primary" />
                          </a>
                          <a
                            href="/#"
                            onClick={(e) => deleteRow(e, staff.staffId)}
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
          <Modal.Title>Nhân viên</Modal.Title>
        </Modal.Header>
        <form onSubmit={formik.handleSubmit}>
          <Modal.Body>
            <Input
              id="txtStaffCode"
              type="text"
              label="Mã nhân viên"
              maxLength="100"
              frmField={formik.getFieldProps("staffCode")}
              err={formik.touched.staffCode && formik.errors.staffCode}
              errMessage={formik.errors.staffCode}
            ></Input>
            <Input
              id="txtStaffFirstName"
              type="text"
              label="Họ"
              maxLength="100"
              frmField={formik.getFieldProps("staffFirstName")}
              err={
                formik.touched.staffFirstName && formik.errors.staffFirstName
              }
              errMessage={formik.errors.staffFirstName}
            ></Input>
            <Input
              id="txtStaffLastName"
              type="text"
              label="Tên"
              maxLength="100"
              frmField={formik.getFieldProps("staffLastName")}
              err={formik.touched.staffLastName && formik.errors.staffLastName}
              errMessage={formik.errors.staffLastName}
            ></Input>
            <div className="row">
              <div className="col-sm-3 col-form-label">
                <p>Giới tính</p>
              </div>
              <div className="col-sm-8 ml-3">
                <Select
                  className="select w-100"
                  onChange={(value) =>
                    formik.setFieldValue("gender", value.value)
                  }
                  value={formik.values.value}
                  options={selectGender}
                />
              </div>
            </div>
            <Input
              id="txtBirthDay"
              type="text"
              label="Ngày sinh"
              maxLength="100"
              frmField={formik.getFieldProps("birthDay")}
              err={formik.touched.birthDay && formik.errors.birthDay}
              errMessage={formik.errors.birthDay}
            ></Input>
            <Input
              id="txtStaffPhone"
              type="number"
              label="Điện thoại"
              maxLength="100"
              frmField={formik.getFieldProps("staffPhone")}
              err={formik.touched.staffPhone && formik.errors.staffPhone}
              errMessage={formik.errors.staffPhone}
            ></Input>
            <Input
              id="txtStaffAddress"
              type="text"
              label="Địa chỉ"
              maxLength="100"
              frmField={formik.getFieldProps("staffAddress")}
              err={formik.touched.staffAddress && formik.errors.staffAddress}
              errMessage={formik.errors.staffAddress}
            ></Input>
            <Input
              id="txtStaffCMND"
              type="number"
              label="Số chứng minh"
              maxLength="100"
              frmField={formik.getFieldProps("staffCMND")}
              err={formik.touched.staffCMND && formik.errors.staffCMND}
              errMessage={formik.errors.staffCMND}
            ></Input>
            <Input
              id="txtStaffStarDay"
              type="text"
              label="Ngày vào làm"
              maxLength="100"
              frmField={formik.getFieldProps("staffStarDay")}
              err={formik.touched.staffStarDay && formik.errors.staffStarDay}
              errMessage={formik.errors.staffStarDay}
            ></Input>
            <div className="row">
              <div className="col-sm-3 col-form-label">
                <p>Trạng thái</p>
              </div>
              <div className="col-sm-8 ml-3">
                <Select
                  className="select w-100"
                  onChange={(value) =>
                    formik.setFieldValue("staffStatus", value.value)
                  }
                  value={formik.values.value}
                  options={selectSS}
                />
              </div>
            </div>
            <Input
              id="txtUserName"
              type="text"
              label="Tài khoản"
              maxLength="100"
              frmField={formik.getFieldProps("userName")}
              err={formik.touched.userName && formik.errors.userName}
              errMessage={formik.errors.userName}
            ></Input>
            <Input
              id="txtStaffEmail"
              type="text"
              label="Email"
              maxLength="100"
              frmField={formik.getFieldProps("staffEmail")}
              err={formik.touched.staffEmail && formik.errors.staffEmail}
              errMessage={formik.errors.staffEmail}
            ></Input>
            <Input
              id="txtPassword"
              type="password"
              label="Mật khẩu"
              maxLength="100"
              frmField={formik.getFieldProps("password")}
              err={formik.touched.password && formik.errors.password}
              errMessage={formik.errors.password}
            ></Input>
            <div className="row">
              <div className="col-sm-3 col-form-label">
                <p>Chức vụ</p>
              </div>
              <div className="col-sm-8 ml-3">
                <Select
                  className="select w-100"
                  onChange={(value) =>
                    formik.setFieldValue("roles", value.value)
                  }
                  value={formik.values.value}
                  options={selectRole}
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

      <Modal
        show={modalShowEmail}
        onHide={handleModalClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Nhân viên</Modal.Title>
        </Modal.Header>
        <form onSubmit={formikEmail.handleSubmit}>
          <Modal.Body>
            <Input
              id="txtSubject"
              type="text"
              label="Tiêu đề"
              maxLength="100"
              frmField={formikEmail.getFieldProps("subject")}
              err={formikEmail.touched.subject && formikEmail.errors.subject}
              errMessage={formikEmail.errors.subject}
            ></Input>
            <Input
              id="txtBody"
              type="text"
              label="Nội dung"
              maxLength="100"
              frmField={formikEmail.getFieldProps("body")}
              err={formikEmail.touched.body && formikEmail.errors.body}
              errMessage={formikEmail.errors.body}
            ></Input>
            <Input
              id="txtImage"
              type="file"
              label="Tài liệu"
              maxLength="100"
              frmField={formikEmail.getFieldProps("image")}
              err={formikEmail.touched.image && formikEmail.errors.image}
              errMessage={formikEmail.errors.image}
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

export default Staff;
