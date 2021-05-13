import React, { Component } from "react";
import Select from "react-select";

export default ({ onChange, options, value, className }) => {
  const defaultValue = (options, value) => {
    return options ? options.find((option) => option.value === value) : "";
  };
  return (
    <div className="form-group row">
      <div className={className}>
        <Select
          value={defaultValue(options, value)}
          onChange={(value) => {
            onChange(value);
          }}
          options={options}
        />
      </div>
    </div>
  );
};
