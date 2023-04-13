import { PrivateRoutes, PublicRoutes } from "../../models/routes";

export const links = [
    {
      title: "Inicio",
      path: PublicRoutes.HOME,
    },
    {
        title: "Acerca de",
        path: PublicRoutes.ABOUT,
    },
    {
        title: "Contacto",
        path: PublicRoutes.CONTACT,
    },
    {
      title: "Perfil",
      path: PrivateRoutes.PROFILE,
    },
];