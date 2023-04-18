import { Position } from "./Position";
import { User } from "./User";

export type InitialAuth = {
  token: string;
  user: User;
  position: Position;
};
