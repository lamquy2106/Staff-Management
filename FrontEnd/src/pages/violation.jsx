import React, { useState, Fragment, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import Input from "../controll/input";
import { useFormik, yupToFormErrors, withFormik } from "formik";
import * as Yup from "yup";
import violationService from "./../services/violationService";

import MUIDataTable from "mui-datatables";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import { Delete } from "@material-ui/icons";

const Violation = (props) => {
  const [violations, setViolations] = useState([]);
  const [violationsId, setViolationsId] = useState(0);
  const [modalShow, setModalShow] = useState(false);
  const [totalItem, setToltalItem] = useState(0);

  const formik = useFormik({
    initialValues: {
      violationName: "",
    },
    validationSchema: Yup.object({
      violationName: Yup.string().required("Require"),
    }),
    onSubmit: (values) => {
      handleFormSubmit(values);
    },
  });

  const handleModalClose = () => {
    setModalShow(false);
  };
  const handleModalShow = (e, dataId) => {
    if (e) e.preventDefault();
    console.log(dataId);
    setViolationsId(dataId);
    if (dataId > 0) {
      violationService.getById(dataId).then((res) => {
        console.log(res);
        formik.setValues(res);
        setModalShow(true);
      });
    } else {
      formik.resetForm();
      setModalShow(true);
    }
  };

  const deleteRow = (e, dataId) => {
    e.preventDefault();
    violationService.remove(dataId).then((res) => {
      // if (res.errorCode === 0) {
      loadData();
      // } else {
      // }
    });
  };

  const handleFormSubmit = (data) => {
    if (violationsId === 0) {
      violationService.add(data).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    } else {
      violationService.update(violationsId, data).then((res) => {
        // if (res.errorCode === 0) {
        loadData();
        handleModalClose();
        // } else {
        // }
      });
    }
  };

  const loadData = () => {
    violationService.getAll().then((res) => {
      // if(res.errorCode === 0) {
      setViolations(res.violation);
      setToltalItem(res.totalItems);
      console.log("àngbágf");
      // }
    });
  };

  useEffect(() => {
    loadData();
  }, []);
  return (
    <Fragment>
      <div className="container mt-4">
        <div className="card border-primary bt-primary-5">
          <div className="card-header">
            <div className="row">
              <div className="col">
                <h3 className="card-title">Vi phạm</h3>
              </div>
              <div>
                <input
                  placeholder="Tìm kiếm theo lỗi vi phạm"
                  id="txtSearch"
                  type="text"
                  className="form-control"
                ></input>
              </div>
              <div className="col-auto">
                <button type="button" className="btn btn-primary">
                  <i class="fas fa-search"></i>
                </button>
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
              <table className="table table-bordered table-hover table-striped">
                <thead>
                  <tr className="table-primary">
                    <th style={{ width: "50px" }}>{totalItem}</th>
                    <th>Tên trạng thái</th>
                    <th style={{ width: "80px" }} />
                  </tr>
                </thead>
                <tbody>
                  {violations.map((violation, idx) => {
                    return (
                      <tr key={violation.violationId}>
                        <td>
                          {idx + 1}/{totalItem}
                        </td>
                        <td>{violation.violationName}</td>
                        <td>
                          <a
                            href="/#"
                            onClick={(e) =>
                              handleModalShow(e, violation.violationId)
                            }
                          >
                            <i className="fas fa-edit text-primary" />
                          </a>
                          <a
                            href="/#"
                            onClick={(e) => deleteRow(e, violation.violationId)}
                          >
                            <i className="fas fa-trash-alt text-danger ml-2" />
                          </a>
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
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
          <Modal.Title>Vi phạm</Modal.Title>
        </Modal.Header>
        <form onSubmit={formik.handleSubmit}>
          <Modal.Body>
            <Input
              id="txtViolation"
              type="text"
              label="Tên vi phạm"
              maxLength="100"
              frmField={formik.getFieldProps("violationName")}
              err={formik.touched.violationName && formik.errors.violationName}
              errMessage={formik.errors.violationName}
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

export default Violation;
