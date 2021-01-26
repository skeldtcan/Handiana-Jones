import { createInstance } from "./index.js";

const instance = createInstance();
// const config = {
    //   headers: { "access-token": localStorage.getItem("access-token") }
    // };
    
function join(user, success, fail){
    instance
    .post('/user', JSON.stringify(user))
    .then(success)
    .catch(fail)
}
    
function login(user, success, fail) {
  instance.defaults.headers["access-token"] = window.localStorage.getItem(
    "access-token"
  );
  const body = {
    user_id: user.user_id,
    user_password: user.user_password
  };
  console.log(instance.defaults.headers)
  instance
    .post("user/confirm/login", JSON.stringify(body))
    .then(success)
    .catch(fail);
}

async function findById(userid, success, fail) {
  instance.defaults.headers["access-token"] = window.localStorage.getItem(
    "access-token"
  );
  await instance
    .get(`/user/info/${userid}`)
    .then(success)
    .catch(fail);
}

export { join, login, findById };
