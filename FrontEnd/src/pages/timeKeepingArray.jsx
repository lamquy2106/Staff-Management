import React, { useState, Fragment, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import Input from "../controll/input";
import Select from "../controll/select";
import { useFormik, yupToFormError, Field } from "formik";
import * as Yup from "yup";
import staffService from "./../services/staffService";
import salaryService from "./../services/salaryService";

import MUIDataTable from "mui-datatables";
import timeKeepingArrayService from "./../services/timeKeepingArray";

const TimeKeepingArray = (props) => {
  const [timeKeepingArrays, setTimeKeepingArrays] = useState([]);

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
    timeKeepingArrayService.add(result[0].month, result[0].year).then((res) => {
      setTimeKeepingArrays(res.chamcong);
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
      name: "one",
      label: "01",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "two",
      label: "02",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "three",
      label: "03",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "four",
      label: "04",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "five",
      label: "05",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "six",
      label: "06",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "seven",
      label: "07",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "eight",
      label: "08",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "nine",
      label: "09",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "ten",
      label: "10",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "eleven",
      label: "11",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "twele",
      label: "12",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "thirteen",
      label: "13",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "fourteen",
      label: "14",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "fifteen",
      label: "15",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "sixteen",
      label: "16",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "seventeen",
      label: "17",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "eighteen",
      label: "18",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "nineteen",
      label: "19",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "twenty",
      label: "20",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "twentyone",
      label: "21",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "twentytwo",
      label: "22",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "twentythree",
      label: "23",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "twentyfour",
      label: "24",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "twentyfive",
      label: "25",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "twentysix",
      label: "26",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "twentyseven",
      label: "27",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "twentyeight",
      label: "28",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "twentynine",
      label: "29",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "thirty",
      label: "30",
      options: {
        filter: true,
        sort: true,
      },
    },
    {
      name: "thirtyone",
      label: "31",
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
                <h3 className="card-title">Bảng chấm công</h3>
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
                data={timeKeepingArrays}
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

export default TimeKeepingArray;
