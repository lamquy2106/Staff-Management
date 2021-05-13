import React, { useState, Fragment, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import Input from "../controll/input";
import Select from "../controll/select";
import { useFormik, yupToFormError, Field } from "formik";
import * as Yup from "yup";
import projectService from "./../services/projectService";
import staffService from "./../services/staffService";

import MUIDataTable from "mui-datatables";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import { Delete } from "@material-ui/icons";

const Project = (props) => {
  const [staffs, setStaffs] = useState([]);
  const [staffsId, setStaffsId] = useState(0);
  const [modalShow, setModalShow] = useState(false);
  const [projects, setProjects] = useState([]);
  const [projectsId, setProjectsId] = useState(0);
  const [selectedOption, setSelectedOption] = useState(0);
  const [totalItem, setToltalItem] = useState(0);

  const formik = useFormik({
    initialValues: {
      proName: "",
      proPrice: "",
      staffId: 0,
    },
    validationSchema: Yup.object({
      proName: Yup.string().required("Require"),
      proPrice: Yup.number().required("Require"),
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
    setProjectsId(dataId);
    if (dataId != null) {
      projectService.getById(dataId).then((res) => {
        var project = res;
        const result = [];
        result.push({
          proId: project.project.proId,
          proName: project.project.proName,
          proPrice: project.project.proPrice,
          staffId: project.staffId,
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
    projectService.remove(dataId).then((res) => {
      // if (res.errorCode === 0) {
      loadData();
      // } else {
      // }
    });
  };

  const handleFormSubmit = (data) => {
    const result = [];
    result.push({
      proName: data.proName,
      proPrice: data.proPrice,
    });
    if (projectsId == null) {
      projectService.add(result[0], data.staffId).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    } else {
      projectService.update(projectsId, result[0], data.staffId).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    }
  };

  const loadData = () => {
    projectService.getAll().then((res) => {
      // if(res.errorCode === 0) {
      setProjects(res.project);
      setToltalItem(res.totalItems);
      // }
    });
    staffService.getAll().then((res) => {
      var staff = res.staff;
      var totalStaff = staff.length;
      const options = [];
      // const list = [];
      // setToltalItem(res.totalItems);
      for (var i = 0; i < totalStaff; i++) {
        options.push({
          value: staff[i].staffId,
          label: staff[i].staffFirstName + " " + staff[i].staffLastName,
        });
        // let salaryVl = staff[i].salarys[0];
        // console.log(salaryVl);
        // if (salaryVl != null) {
        //   list.push({
        //     staffId: staff[i].staffId,
        //     staffFirstName: staff[i].staffFirstName,
        //     staffLastName: staff[i].staffLastName,
        //     salaryId: salaryVl.salaryId,
        //     coefficientsSalary: salaryVl.coefficientsSalary,
        //     basicSalary: salaryVl.basicSalary,
        //     totalSalary: salaryVl.totalSalary,
        //     rewardMoney: salaryVl.rewardMoney,
        //     punishMoney: salaryVl.punishMoney,
        //     prepayMoney: salaryVl.prepayMoney,
        //   });
        // } else {
        //   list.push({
        //     staffId: staff[i].staffId,
        //     staffFirstName: staff[i].staffFirstName,
        //     staffLastName: staff[i].staffLastName,
        //   });
        // }
      }
      // if(res.errorCode === 0) {
      setStaffs(options);
      // setLists(list);
      // }
    });
  };

  useEffect(() => {
    loadData();
  }, []);
  const columns = [
    {
      name: "proId",
      label: "Id dự án",
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
      name: "proName",
      label: "Tên dự án",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "proPrice",
      label: "Giá dự án",
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
                <h3 className="card-title">Dự án</h3>
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
                data={projects}
                columns={columns}
                options={options}
              />
              {/* <table className="table table-bordered table-hover table-striped">
                <thead>
                  <tr className="table-primary">
                    <th style={{ width: "50px" }}>{totalItem}</th>
                    <th>Tên dự án</th>
                    <th>Giá dự án</th>
                    <th style={{ width: "80px" }} />
                  </tr>
                </thead>
                <tbody>
                  {projects.map((project, idx) => {
                    return (
                      <tr key={project.proId}>
                        <td>
                          {idx + 1}/{totalItem}
                        </td>
                        <td>{project.proName}</td>
                        <td>{project.proPrice}</td>
                        <td>
                          <a
                            href="/#"
                            onClick={(e) => handleModalShow(e, project.proId)}
                          >
                            <i className="fas fa-edit text-primary" />
                          </a>
                          <a
                            href="/#"
                            onClick={(e) => deleteRow(e, project.proId)}
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
          <Modal.Title>Dự án</Modal.Title>
        </Modal.Header>
        <form onSubmit={formik.handleSubmit}>
          <Modal.Body>
            <Input
              id="txtName"
              type="text"
              label="Tên dự án"
              maxLength="100"
              frmField={formik.getFieldProps("proName")}
              err={formik.touched.proName && formik.errors.proName}
              errMessage={formik.errors.proName}
            ></Input>
            <Input
              id="txtPrice"
              type="number"
              label="Giá dự án"
              maxLength="100"
              frmField={formik.getFieldProps("proPrice")}
              err={formik.touched.proPrice && formik.errors.proPrice}
              errMessage={formik.errors.proPrice}
            ></Input>
            <div className="row">
              <div className="col-sm-3 col-form-label">
                <p>Quản lý dự án</p>
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

export default Project;
