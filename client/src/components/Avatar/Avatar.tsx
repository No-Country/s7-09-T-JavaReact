import { FaUserCircle } from "react-icons/fa";

interface Iprops {
  image_url: string | null;
  size: "xs" | "sm" | "md" | "lg" | "xl";
}

const Avatar = ({ image_url, size }: Iprops) => {
  const sizes = {
    xs: "40px",
    sm: "48px",
    md: "60px",
    lg: "100px",
    xl: "161px",
  };
  return (
    <>
      <div
        className={`flex bg-[#FF5C00] rounded-full justify-center items-center`}
        style={{ width: sizes[size], height: sizes[size] }}
      >
        {image_url ? (
          <img src={image_url} alt={"avatar-name"} />
        ) : (
          <FaUserCircle size={sizes[size]} />
        )}
      </div>
    </>
  );
};

export default Avatar;
