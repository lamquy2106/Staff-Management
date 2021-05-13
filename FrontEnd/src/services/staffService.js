const { default: api } = require("./api");

const getAll = () => {
  return api.get(api.url.staff).then((res) => res.data);
};

const add = (data) => {
  return api.post(`${api.url.baseUrl}/staff`, data).then((res) => res.data);
};

const getById = (id) =>
  api.get(`${api.url.staff}/${id}`).then((res) => res.data);

const update = (id, data) =>
  api.put(`${api.url.baseUrl}/staff/${id}`, data).then((res) => res.data);

const remove = (id) =>
  api.delete(`${api.url.staff}/${id}`).then((res) => res.data);

const sendEmail = (id, data) => {
  return api
    .post(`${api.url.baseUrl}/sendemail/${id}`, data)
    .then((res) => res.data);
};
const staffService = {
  getAll,
  add,
  getById,
  update,
  remove,
  sendEmail,
};

export default staffService;
