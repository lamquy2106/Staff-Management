const { default: api } = require("./api");

const getAll = (month, year) => {
  return api
    .get(`${api.url.baseUrl}/salaryinmonth/${month}/${year}`)
    .then((res) => res.data);
};
const add = (month, year) => {
  return api
    .post(`${api.url.baseUrl}/salaryinmonth/${month}/${year}`)
    .then((res) => res.data);
};
const salaryInMonthService = {
  getAll,
  add,
};

export default salaryInMonthService;
