export const getLocalStorage = (key: string) => {
  const item = localStorage.getItem(key);

  if (!item) return;
  try {
    return JSON.parse(item);
  } catch (error) {
    console.log(error);
  }
  return item;
};

export const setLocalStorage = <T>(key: string, value: T) => {
  localStorage.setItem(key, JSON.stringify({ ...value }));
};

export const clearLocalStorage = (key: string) => {
  localStorage.removeItem(key);
};
