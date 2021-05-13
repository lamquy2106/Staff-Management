const { default: api } = require("./api");

const getAll = () => {
  return api.get(api.url.roomProject).then((res) => res.data);
};

const add = (data, proId, roomId) => {
  return api
    .post(
      `${api.url.baseUrl}/project/${proId}/room/${roomId}/roomproject`,
      data
    )
    .then((res) => res.data);
};

const getById = (id) =>
  api.get(`${api.url.roomProject}/${id}`).then((res) => res.data);

const update = (id, data, proId, roomId) =>
  api
    .put(
      `${api.url.baseUrl}/project/${proId}/room/${roomId}/roomproject/${id}`,
      data
    )
    .then((res) => res.data);

const remove = (id) =>
  api.delete(`${api.url.roomProject}/${id}`).then((res) => res.data);
const roomProjectService = {
  getAll,
  add,
  getById,
  update,
  remove,
};

export default roomProjectService;
