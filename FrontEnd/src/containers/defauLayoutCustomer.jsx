import React, { Component, Fragment } from "react";
import routes from "./../routes";
import { Switch, Route } from "react-router-dom";
import Footer from "./footer";

class DefauLayoutCustomer extends Component {
    state = {};
    render() {
      return (
        <Fragment>
          <div>
            <div id="wrapper">
              <LeftSite />
              <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                  <Header />
  
                  <Switch>
                    {routes.map((route, idx) => {
                      return route.component ? (
                        <Route
                          key={idx}
                          path={route.path}
                          exact={route.exact}
                          name={route.name}
                          component={route.component}
                        />
                      ) : null;
                    })}
                  </Switch>
                </div>
                <Footer />
              </div>
            </div>
          </div>
        </Fragment>
      );
    }
  }
  
  export default DefauLayoutCustomer;
  