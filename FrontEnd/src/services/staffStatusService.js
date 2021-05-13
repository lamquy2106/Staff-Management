const { default: api } = require("./api");

const getAll = () => {
  return api.get(api.url.staffStatus).then((res) => res.data);
};

const add = (data) => {
  return api.post(api.url.staffStatus, data).then((res) => res.data);
};

const getById = (id) =>
  api.get(`${api.url.staffStatus}/${id}`).then((res) => res.data);

const update = (id, data) =>
  api.put(`${api.url.staffStatus}/${id}`, data).then((res) => res.data);

const remove = (id) =>
  api.delete(`${api.url.staffStatus}/${id}`).then((res) => res.data);
const staffStatusService = {
  getAll,
  add,
  getById,
  update,
  remove,
};

export default staffStatusService;
