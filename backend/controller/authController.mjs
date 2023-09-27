import PocketBase from "pocketbase";

const pb = new PocketBase("https://fit-me.pockethost.io");

//const auth for the user
const authUser = async (req, res) => {
  const { email, password } = req.body;
  const authData = await pb
    .collection("users")
    .authWithPassword(email, password);

  // after the above you can also access the auth data from the authStore
  console.log(pb.authStore.isValid);
  console.log(pb.authStore.token);
  console.log(pb.authStore.model.id);

  // "logout" the last authenticated account
  pb.authStore.clear();
};
export { authUser };