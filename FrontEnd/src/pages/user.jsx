import React, { Component, useState, Fragment, useEffect } from "react";
import { Button, Modal } from "react-bootstrap";
import { Link } from "react-router-dom/cjs/react-router-dom.min";
import Input from "../controll/input";
import Select from "../controll/select";
import { useFormik, yupToFormError, Field } from "formik";
import * as Yup from "yup";
import staffStatusService from "./../services/staffStatusService";
import staffService from "./../services/staffService";
import { connect } from "react-redux";
import ActionTypes from "../store/action";
import MUIDataTable from "mui-datatables";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import { Delete } from "@material-ui/icons";

const User = (props) => {
  const [salarys, setSalarys] = useState([]);
  const [tks, setTks] = useState([]);

  const loadData = () => {
    const dataId = localStorage.getItem("userId");
    console.log(dataId);
    staffService.getById(dataId).then((res) => {
      // if(res.errorCode === 0) {
      console.log(res);
      setSalarys(res.salarys);
      setTks(res.timeKeepings);
      // console.log(res);
      // }
    });
  };

  useEffect(() => {
    loadData();
    console.log(localStorage.getItem("userId"));
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
  ];

  const columntks = [
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
        <div>
          <nav className="navbar navbar-expand navbar-light bg-primary topbar mb-4 static-top shadow">
            <ul className="navbar-nav ml-auto">
              <li className="nav-item dropdown no-arrow mx-1">
                <div
                  className="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                  aria-labelledby="messagesDropdown"
                >
                  <h6 className="dropdown-header">Message Center</h6>
                  <a
                    className="dropdown-item d-flex align-items-center"
                    href="#"
                  >
                    <div className="dropdown-list-image mr-3">
                      <img
                        className="rounded-circle"
                        src="https://source.unsplash.com/fn_BT9fwg_E/60x60"
                        alt=""
                      />
                      <div className="status-indicator bg-success" />
                    </div>
                    <div className="font-weight-bold">
                      <div className="text-truncate">
                        Hi there! I am wondering if you can help me with a
                        problem I've been having.
                      </div>
                      <div className="small text-gray-500">
                        Emily Fowler · 58m
                      </div>
                    </div>
                  </a>
                  <a
                    className="dropdown-item d-flex align-items-center"
                    href="#"
                  >
                    <div className="dropdown-list-image mr-3">
                      <img
                        className="rounded-circle"
                        src="https://source.unsplash.com/AU4VPcFN4LE/60x60"
                        alt=""
                      />
                      <div className="status-indicator" />
                    </div>
                    <div>
                      <div className="text-truncate">
                        I have the photos that you ordered last month, how would
                        you like them sent to you?
                      </div>
                      <div className="small text-gray-500">Jae Chun · 1d</div>
                    </div>
                  </a>
                  <a
                    className="dropdown-item d-flex align-items-center"
                    href="#"
                  >
                    <div className="dropdown-list-image mr-3">
                      <img
                        className="rounded-circle"
                        src="https://source.unsplash.com/CS2uCrpNzJY/60x60"
                        alt=""
                      />
                      <div className="status-indicator bg-warning" />
                    </div>
                    <div>
                      <div className="text-truncate">
                        Last month's report looks great, I am very happy with
                        the progress so far, keep up the good work!
                      </div>
                      <div className="small text-gray-500">
                        Morgan Alvarez · 2d
                      </div>
                    </div>
                  </a>
                  <a
                    className="dropdown-item d-flex align-items-center"
                    href="#"
                  >
                    <div className="dropdown-list-image mr-3">
                      <img
                        className="rounded-circle"
                        src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                        alt=""
                      />
                      <div className="status-indicator bg-success" />
                    </div>
                    <div>
                      <div className="text-truncate">
                        Am I a good boy? The reason I ask is because someone
                        told me that people say this to all dogs, even if they
                        aren't good...
                      </div>
                      <div className="small text-gray-500">
                        Chicken the Dog · 2w
                      </div>
                    </div>
                  </a>
                  <a
                    className="dropdown-item text-center small text-gray-500"
                    href="#"
                  >
                    Read More Messages
                  </a>
                </div>
              </li>
              <div className="topbar-divider d-none d-sm-block" />
              <li className="nav-item dropdown no-arrow">
                <a
                  className="nav-link dropdown-toggle"
                  href="#"
                  id="userDropdown"
                  role="button"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                  <span className="mr-2 d-none d-lg-inline text-gray-600 small">
                    Valerie Luna
                  </span>
                  <img
                    className="img-profile rounded-circle"
                    src="https://source.unsplash.com/QAB-WJcbgJk/60x60"
                  />
                </a>
                <div
                  className="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                  aria-labelledby="userDropdown"
                >
                  <a className="dropdown-item" href="#">
                    <i className="fas fa-user fa-sm fa-fw mr-2 text-gray-400" />
                    Profile
                  </a>
                  <a className="dropdown-item" href="#">
                    <i className="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400" />
                    Settings
                  </a>
                  <a className="dropdown-item" href="#">
                    <i className="fas fa-list fa-sm fa-fw mr-2 text-gray-400" />
                    Activity Log
                  </a>
                  <div className="dropdown-divider" />
                  <Link
                    className="collapse-item btn"
                    onClick={() => {
                      localStorage.removeItem("userId");
                    }}
                    to="/login"
                  >
                    <i className="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400" />
                    Đăng xuất
                  </Link>
                </div>
              </li>
            </ul>
          </nav>
        </div>
        <div className="card border-primary bt-primary-5">
          <div className="card-header">
            <div className="row">
              <div className="col">
                <h3 className="card-title">Lương</h3>
              </div>
            </div>
          </div>
          <div className="card-body">
            <div className="table-responsive">
              <MUIDataTable
                title={""}
                data={salarys}
                columns={columns}
                options={options}
              />
            </div>
          </div>
        </div>
      </div>
      <div className="container mt-4">
        <div className="card border-primary bt-primary-5">
          <div className="card-header">
            <div className="row">
              <div className="col">
                <h3 className="card-title">Điểm danh</h3>
              </div>
            </div>
          </div>
          <div className="card-body">
            <div className="table-responsive">
              <MUIDataTable
                title={""}
                data={tks}
                columns={columntks}
                options={options}
              />
            </div>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

export default User;
