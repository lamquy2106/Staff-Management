const { default: api } = require("./api");

const getAll = (month, year) => {
  return api
    .get(`${api.url.baseUrl}/xuatchamcong/${month}/${year}`)
    .then((res) => res.data);
};
const add = (month, year) => {
  return api
    .post(`${api.url.baseUrl}/bangchamcong/${month}/${year}`)
    .then((res) => res.data);
};
const timeKeepingArrayService = {
  getAll,
  add,
};

export default timeKeepingArrayService;
