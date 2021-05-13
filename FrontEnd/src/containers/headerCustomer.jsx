import { Callbacks } from "jquery";
import React, { Component } from "react";
import "./sa.css";
import { Link } from "react-router-dom/cjs/react-router-dom.min";

class HeaderCustomer extends Component {
  state = {};
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <div className="container">
            <div
              className="collapse navbar-collapse"
              id="navbarSupportedContent"
            >
              <ul className="navbar-nav mr-auto">
                <li>
                  <i className="fas fa-phone rgtspace-5" /> +00 (123) 456 7890
                </li>
                <li className="ml-3">
                  <i className="far fa-envelope rgtspace-5 mr-2" />{" "}
                  info@domain.com
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <nav
          className="navbar navbar-expand-lg navbar-dark text-light"
          style={{ backgroundColor: "#53D3DE" }}
        >
          <div className="container">
            <div
              className="collapse navbar-collapse"
              id="navbarSupportedContent"
            >
              <ul className="navbar-nav mr-auto">
                <li>
                  <h1 className="p-3">LOGO HERE</h1>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
    );
  }
}

export default HeaderCustomer;
