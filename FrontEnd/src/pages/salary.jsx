import React, { useState, Fragment, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import Input from "../controll/input";
import Select from "../controll/select";
import { useFormik, yupToFormError, Field } from "formik";
import * as Yup from "yup";
import staffService from "./../services/staffService";
import salaryService from "./../services/salaryService";

import MUIDataTable from "mui-datatables";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import { Delete } from "@material-ui/icons";

const Salary = (props) => {
  const [staffs, setStaffs] = useState([]);
  const [staffsId, setStaffsId] = useState(0);
  const [lists, setLists] = useState([]);
  const [modalShow, setModalShow] = useState(false);
  const [salarys, setSalarys] = useState([]);
  const [salarysId, setSalarysId] = useState(0);
  const [selectedOption, setSelectedOption] = useState(0);
  const [totalItem, setToltalItem] = useState(0);

  const formik = useFormik({
    initialValues: {
      basicSalary: "",
      rewardMoney: "",
      prepayMoney: "",
      subsidize: "",
      staffId: 0,
    },
    validationSchema: Yup.object({
      basicSalary: Yup.number().required("Xin nhập số"),
      rewardMoney: Yup.number().required("Xin nhập số"),
      prepayMoney: Yup.number().required("Xin nhập số"),
      subsidize: Yup.number().required("Xin nhập số"),
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
    setSalarysId(dataId);
    if (dataId != null) {
      salaryService.getById(dataId).then((res) => {
        var salary = res;
        const result = [];
        result.push({
          salaryId: salary.salary.salaryId,
          subsidize: salary.salary.subsidize,
          basicSalary: salary.salary.basicSalary,
          rewardMoney: salary.salary.rewardMoney,
          prepayMoney: salary.salary.prepayMoney,
          staffId: salary.staffId,
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
    salaryService.remove(dataId).then((res) => {
      // if (res.errorCode === 0) {
      loadData();
      // } else {
      // }
    });
  };

  const handleFormSubmit = (data) => {
    const result = [];

    result.push({
      subsidize: data.subsidize,
      basicSalary: data.basicSalary,
      rewardMoney: data.rewardMoney,
      prepayMoney: data.prepayMoney,
    });
    if (salarysId == null) {
      salaryService.add(result[0], data.staffId).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    } else {
      salaryService.update(salarysId, result[0], data.staffId).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    }
  };

  const loadData = () => {
    salaryService.getAll().then((res) => {
      // if(res.errorCode === 0) {
      setSalarys(res.salary);

      // }
    });
    staffService.getAll().then((res) => {
      var staff = res.staff;
      var totalStaff = staff.length;
      const options = [];
      const list = [];
      setToltalItem(res.totalItems);
      for (var i = 0; i < totalStaff; i++) {
        options.push({
          value: staff[i].staffId,
          label: staff[i].staffFirstName + " " + staff[i].staffLastName,
        });
        let salaryVl = staff[i].salarys[0];
        console.log(salaryVl);
        if (salaryVl != null) {
          list.push({
            staffId: staff[i].staffId,
            staffFirstName: staff[i].staffFirstName,
            staffLastName: staff[i].staffLastName,
            salaryId: salaryVl.salaryId,
            subsidize: salaryVl.subsidize,
            basicSalary: salaryVl.basicSalary,
            rewardMoney: salaryVl.rewardMoney,
            prepayMoney: salaryVl.prepayMoney,
          });
        } else {
          list.push({
            staffId: staff[i].staffId,
            staffFirstName: staff[i].staffFirstName,
            staffLastName: staff[i].staffLastName,
          });
        }
      }
      // if(res.errorCode === 0) {
      setStaffs(options);
      setLists(list);
      // }
    });
  };

  useEffect(() => {
    loadData();
  }, []);

  const columns = [
    {
      name: "salaryId",
      label: "Id lương",
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
      name: "basicSalary",
      label: "Lương cơ bản",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "rewardMoney",
      label: "Tiền thưởng",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "prepayMoney",
      label: "Tiền tạm ứng",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "subsidize",
      label: "Phụ cấp",
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
                <h3 className="card-title">Lương</h3>
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
                data={lists}
                columns={columns}
                options={options}
              />
              {/* <table className="table table-bordered table-hover table-striped">
                <thead>
                  <tr className="table-primary">
                    <th style={{ width: "50px" }}>{totalItem}</th>
                    <th>Nhân viên</th>
                    <th>Hệ số Lương</th>
                    <th>Lương cơ bản</th>
                    <th>Tiền thưởng</th>
                    <th>Tiền phạt</th>
                    <th>Tiền ứng trước</th>
                    <th>Tổng tiền</th>
                    <th style={{ width: "80px" }} />
                  </tr>
                </thead>
                <tbody>
                  {lists.map((list, idx) => {
                    return (
                      <tr key={list.staffId}>
                        <td>
                          {idx + 1}/{totalItem}
                        </td>
                        <td>
                          {list.staffFirstName + " " + list.staffLastName}
                        </td>
                        <td>{list.coefficientsSalary}</td>
                        <td>{list.basicSalary}</td>
                        <td>{list.rewardMoney}</td>
                        <td>{list.punishMoney}</td>
                        <td>{list.prepayMoney}</td>
                        <td>{list.totalSalary}</td>
                        <td>
                          <a
                            href="/#"
                            onClick={(e) => handleModalShow(e, list.salaryId)}
                          >
                            <i className="fas fa-edit text-primary" />
                          </a>
                          <a
                            href="/#"
                            onClick={(e) => deleteRow(e, list.salaryId)}
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
          <Modal.Title>Lương</Modal.Title>
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
              id="txtBasicSalary"
              type="number"
              label="Lương cơ bản"
              maxLength="100"
              frmField={formik.getFieldProps("basicSalary")}
              err={formik.touched.basicSalary && formik.errors.basicSalary}
              errMessage={formik.errors.basicSalary}
            ></Input>
            <Input
              id="txtRewardMoney"
              type="number"
              label="Tiền thưởng"
              maxLength="100"
              frmField={formik.getFieldProps("rewardMoney")}
              err={formik.touched.rewardMoney && formik.errors.rewardMoney}
              errMessage={formik.errors.rewardMoney}
            ></Input>
            <Input
              id="txtPrepayMoney"
              type="number"
              label="Tiền ứng trước"
              maxLength="100"
              frmField={formik.getFieldProps("prepayMoney")}
              err={formik.touched.prepayMoney && formik.errors.prepayMoney}
              errMessage={formik.errors.prepayMoney}
            ></Input>
            <Input
              id="txtSubsidize"
              type="number"
              label="Phụ cấp"
              maxLength="100"
              frmField={formik.getFieldProps("subsidize")}
              err={formik.touched.subsidize && formik.errors.subsidize}
              errMessage={formik.errors.subsidize}
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

export default Salary;
