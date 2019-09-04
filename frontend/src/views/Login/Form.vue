<template>
  <div class="row justify-content-center">
    <div class="col-4">
      <h1 class="text-center">Login</h1>
      <b-form>
        <b-form-group label="Email" description="Ex.: exemplo@exemplo.com">
          <b-form-input
            v-model="form.email"
            type="email"
            required
            placeholder="Digite seu e-mail de acesso"
          ></b-form-input>
        </b-form-group>

        <b-form-group label="Password">
          <b-form-input v-model="form.password" type="password" required></b-form-input>
        </b-form-group>
        <b-button class="text-center" type="submit" variant="primary">Login</b-button>
      </b-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        email: "",
        password: ""
      }
    };
  }
};
</script>