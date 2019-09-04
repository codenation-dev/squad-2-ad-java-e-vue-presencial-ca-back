<template>
  <div>
    <Navbar></Navbar>
    <Container>
      <FormLogin></FormLogin>
    </Container>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar";
import Container from "@/components/Container";
import FormLogin from "@/views/Login/Form";

export default {
  data() {
    return {};
  },
  components: { Navbar, Container, FormLogin }
};
</script>