import { lazy, Suspense } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { PrivateRoutes, PublicRoutes } from "../models/routes";
import Home from "../pages/Home/Home";
import { RequireAuth } from "./RequireAuth";
import Navbar from "../components/Navbar/Navbar";
import Spinner from "../components/Spinner/Spinner";
import Footer from "../components/Footer/Footer";

const About = lazy(() => import("../pages/About/About"));
const Contact = lazy(() => import("../pages/Contact/Contact"));
const Profile = lazy(() => import("../pages/Profile/Profile"));
const ExperienceDetail = lazy(
  () => import("../pages/ExperienceDetail/ExperienceDetail")
);
const Register = lazy(() => import("../pages/Register/Register"));
const Login = lazy(() => import("../pages/Login/Login"));
const NotFound = lazy(() => import("../components/NotFound/NotFound"));

const AppRouter = () => {
  return (
    <Suspense fallback={<Spinner />}>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path={PublicRoutes.HOME} element={<Home />} />
          <Route
            path={PublicRoutes.EXPERIENCE_DETAIL}
            element={<ExperienceDetail />}
          />
          <Route path={PublicRoutes.ABOUT} element={<About />} />
          <Route path={PublicRoutes.CONTACT} element={<Contact />} />
          <Route path={PublicRoutes.LOGIN} element={<Login />} />
          <Route path={PublicRoutes.REGISTER} element={<Register />} />
          <Route
            path={PrivateRoutes.PROFILE}
            element={
              <RequireAuth>
                <Profile />
              </RequireAuth>
            }
          />
          <Route path="*" element={<NotFound />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </Suspense>
  );
};

export default AppRouter;
