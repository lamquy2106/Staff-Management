import React, { Component } from "react";
import "./sa.css";
import { Link } from "react-router-dom/cjs/react-router-dom.min";
class LeftSite extends Component {
  state = {};
  render() {
    return (
      <div>
        <ul
          className="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
          id="accordionSidebar"
        >
          {/* Sidebar - Brand */}
          <Link
            className="sidebar-brand d-flex align-items-center justify-content-center"
            to="Trang chủ"
          >
            <div className="sidebar-brand-icon rotate-n-15">
              <i className="fas fa-laugh-wink" />
            </div>
            <div className="sidebar-brand-text mx-3">
              THIẾT BỊ ĐIỆN TỬ THỦ DẦU MỘT
            </div>
          </Link>
          {/* Divider */}
          <hr className="sidebar-divider my-0" />
          {/* Nav Item - Dashboard */}
          <li className="nav-item active">
            <Link className="nav-link" to="home">
              <i className="fas fa-fw fa-tachometer-alt" />
              <span>Trang chủ</span>
            </Link>
          </li>
          {/* Divider */}
          <hr className="sidebar-divider" />
          {/* Heading */}
          <div className="sidebar-heading">Interface</div>
          {/* Nav Item - Pages Collapse Menu */}
          <li className="nav-item">
            <Link
              className="nav-link collapsed"
              data-toggle="collapse"
              data-target="#collapseTwo"
              aria-expanded="true"
              aria-controls="collapseTwo"
            >
              <i class="fas fa-utensils"></i>
              <span>Quản lý nhân sự</span>
            </Link>
            <div
              id="collapseTwo"
              className="collapse"
              aria-labelledby="headingTwo"
              data-parent="#accordionSidebar"
            >
              <div className="bg-white py-2 collapse-inner rounded">
                <h6 className="collapse-header">Quản lý thông tin:</h6>
                <Link className="collapse-item" to="staff">
                  Nhân viên
                </Link>
                <h6 className="collapse-header">Quản lý Lương:</h6>
                <Link className="collapse-item" to="salary">
                  Lương
                </Link>
                <Link className="collapse-item" to="salaryinmonth">
                  Bảng lương
                </Link>
                <h6 className="collapse-header">Quản lý chuyên cần:</h6>
                <Link className="collapse-item" to="timekeeping">
                  Điểm danh
                </Link>
                <Link className="collapse-item" to="timekeepingarray">
                  Chấm công
                </Link>
              </div>
            </div>
          </li>
          {/* Nav Item - Utilities Collapse Menu */}
          <li className="nav-item">
            <Link
              className="nav-link collapsed"
              data-toggle="collapse"
              data-target="#collapseUtilities"
              aria-expanded="true"
              aria-controls="collapseUtilities"
            >
              <i class="fas fa-warehouse"></i>
              <span>Quản lý dự án</span>
            </Link>
            <div
              id="collapseUtilities"
              className="collapse"
              aria-labelledby="headingUtilities"
              data-parent="#accordionSidebar"
            >
              <div className="bg-white py-2 collapse-inner rounded">
                <h6 className="collapse-header">Quản lý dự án:</h6>
                <Link className="collapse-item" to="project">
                  Dự án
                </Link>
                <Link className="collapse-item" to="roomproject">
                  Phòng - Dự án
                </Link>
              </div>
            </div>
          </li>
          {/* Divider */}
          <hr className="sidebar-divider" />
          {/* Heading */}
          <div className="sidebar-heading">Addons</div>
          {/* Nav Item - Pages Collapse Menu */}
          <li className="nav-item">
            <Link
              className="nav-link collapsed"
              data-toggle="collapse"
              data-target="#collapsePages"
              aria-expanded="true"
              aria-controls="collapsePages"
            >
              <i class="fas fa-user-alt"></i>
              <span>Quản lý Phòng ban</span>
            </Link>
            <div
              id="collapsePages"
              className="collapse"
              aria-labelledby="headingPages"
              data-parent="#accordionSidebar"
            >
              <div className="bg-white py-2 collapse-inner rounded">
                <h6 className="collapse-header">Quản lý phòng ban:</h6>
                <Link className="collapse-item" to="room">
                  Phòng ban
                </Link>
                <Link className="collapse-item" to="staffroom">
                  Nhân viên - Phòng ban
                </Link>
              </div>
            </div>
          </li>
          {/* Nav Item - Charts */}
        </ul>
      </div>
    );
  }
}

export default LeftSite;
