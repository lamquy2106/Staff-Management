const { default: api } = require("./api");


const login = (userName, password) => {
  return api
    .post(`${api.url.baseUrl}/login/${userName}/${password}`)
    .then((res) => res.data);
};
const loginService = {
  login,
};

export default loginService;
