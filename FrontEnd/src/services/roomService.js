const { default: api } = require("./api");

const getAll = () => {
  return api.get(api.url.room).then((res) => res.data);
};

const add = (data) => {
  return api.post(api.url.room, data).then((res) => res.data);
};

const getById = (id) =>
  api.get(`${api.url.room}/${id}`).then((res) => res.data);

const update = (id, data) =>
  api.put(`${api.url.room}/${id}`, data).then((res) => res.data);

const remove = (id) =>
  api.delete(`${api.url.room}/${id}`).then((res) => res.data);
const roomService = {
  getAll,
  add,
  getById,
  update,
  remove,
};

export default roomService;
