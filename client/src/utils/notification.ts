import Swal, { SweetAlertIcon } from "sweetalert2";

export const Error = (title: string) => {
  const toast = Swal.mixin({
    showConfirmButton: false,
    timer: 3000,
  });

  toast.fire({
    icon: "error",
    title: title,
  });
};

export const Success = (title: string, text: string) => {
  const toast = Swal.mixin({
    showConfirmButton: false,
    timer: 2000,
    iconColor: "#13C296",
  });

  toast.fire({
    icon: "success",
    title: title,
    text: text,
  });
};

export const ErrorFormAdmin = (text: string) => {
  Swal.fire({
    icon: "error",
    title: "Oops...",
    text: text,
  });
};

export const Toast = (success: SweetAlertIcon, title: string) => {
  const Toast = Swal.mixin({
    toast: true,
    position: "top-end",
    showConfirmButton: false,
    timer: 2000,
  });

  Toast.fire({
    icon: success,
    title: title,
  });
};

export const confirmToast = (
  success: SweetAlertIcon,
  title: string,
  text: string,
  confirmButtonText: string
) => {
  Swal.fire({
    title: title,
    text: text,
    icon: success,
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: confirmButtonText,
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire("La accion se realizo con exito!", "success");
      return true
    }
    return false
  });
};
