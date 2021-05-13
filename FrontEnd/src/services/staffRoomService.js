const { default: api } = require("./api");

const getAll = () => {
  return api.get(api.url.staffRoom).then((res) => res.data);
};

const add = (data, staffId, roomId) => {
  return api
    .post(`${api.url.baseUrl}/staff/${staffId}/room/${roomId}/staffroom`, data)
    .then((res) => res.data);
};

const getById = (id) =>
  api.get(`${api.url.staffRoom}/${id}`).then((res) => res.data);

const update = (id, data, staffId, roomId) =>
  api
    .put(
      `${api.url.baseUrl}/staff/${staffId}/room/${roomId}/staffroom/${id}`,
      data
    )
    .then((res) => res.data);

const remove = (id) =>
  api.delete(`${api.url.staffRoom}/${id}`).then((res) => res.data);
const staffRoomService = {
  getAll,
  add,
  getById,
  update,
  remove,
};

export default staffRoomService;
