import React, { useState, Fragment, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import Input from "../controll/input";
import Select from "../controll/select";
import { useFormik, yupToFormError, Field } from "formik";
import * as Yup from "yup";
import staffService from "./../services/staffService";
import salaryService from "./../services/salaryService";

import MUIDataTable from "mui-datatables";
import salaryInMonthService from "./../services/salaryInMonthService";

const SalaryInMonth = (props) => {
  const [salaryInMonths, setSalaryInMonths] = useState([]);

  const formik = useFormik({
    initialValues: {
      month: "",
      year: "",
    },
    validationSchema: Yup.object({
      month: Yup.number().required("Xin nhập số"),
      year: Yup.number().required("Xin nhập số"),
    }),
    onSubmit: (values) => {
      handleFormSubmit(values);
    },
  });
  const handleFormSubmit = (data) => {
    const result = [];

    result.push({
      month: data.month,
      year: data.year,
    });
    salaryInMonthService.add(result[0].month, result[0].year).then((res) => {
      setSalaryInMonths(res.SalaryInMonth);
    });
    formik.resetForm();
  };

  const columns = [
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
      name: "firstName",
      label: "Họ",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "lastName",
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
      name: "totalSalary",
      label: "Tổng lương",
      options: {
        filter: true,
        sort: true,
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
                <h3 className="card-title">Bảng lương</h3>
              </div>
              <form onSubmit={formik.handleSubmit}>
                <div className="row">
                  <div className="col-5">
                    <Input
                      id="txtMonth"
                      type="number"
                      label="Tháng"
                      maxLength="100"
                      frmField={formik.getFieldProps("month")}
                      err={formik.touched.month && formik.errors.month}
                      errMessage={formik.errors.month}
                    ></Input>
                  </div>
                  <div className="col-5">
                    <Input
                      id="txtYear"
                      type="number"
                      label="Năm"
                      maxLength="100"
                      frmField={formik.getFieldProps("year")}
                      err={formik.touched.year && formik.errors.year}
                      errMessage={formik.errors.year}
                    ></Input>
                  </div>

                  <div className="col-2">
                    <button
                      type="submit"
                      className="btn btn-primary"
                      // onClick={() => handleFormSubmit(month, year)}
                    >
                      <i class="fas fa-sign-out-alt"></i> Xuất
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
          <div className="card-body">
            <div className="table-responsive">
              <MUIDataTable
                title={""}
                data={salaryInMonths}
                columns={columns}
                options={options}
              />
            </div>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

export default SalaryInMonth;
