const { default: api } = require("./api");

const sendEmail = (id) => {
  return api.post(`${api.url.baseUrl}/sendemail/${id}`).then((res) => res.data);
};
const sendEmailService = {
  sendEmail,
};

export default sendEmailService;
