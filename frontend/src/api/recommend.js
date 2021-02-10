import { createInstance } from "./index.js";

const instance = createInstance();

function recommends(user_no, success, fail){
    instance
    .get(`/heritage/recommend/${user_no}`)
    .then(success)
    .catch(fail)
}

export { recommends, };