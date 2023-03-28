const categories = [
  "Entretenimiento",
  "Turismo",
  "Comida",
  "Educación",
  "Aventura",
  "Actividades familiares",
];
const cards = ["card1", "card2", "card3", "card4", "card5", "card6", "card7", "card8", "card9"];

const Home = () => {
  return (
    <div className="w-full flex flex-col justify-between pb-6">
      <div className="w-full max-w-6xl flex flex-col md:mx-auto gap-9">
        <div className="w-full h-72 bg-red-400 flex items-center justify-center text-3xl text-white font-bold">
          Lorem impsum
        </div>
        <div className="w-full flex flex-col gap-6 px-6 xl:px-0">
          <input
            className="w-full bg-slate-200 p-2 border-solid border-black placeholder:text-black"
            type="search"
            name="search"
            id="search"
            placeholder="Buscar..."
          />
          <div className="flex gap-x-6 md:gap-x-9 items-center md:mx-auto overflow-x-scroll md:overflow-x-hidden font-medium">
            {categories
              ? categories.map((category) => <p key={category}>{category}</p>)
              : null}
          </div>
          <div className="w-full flex flex-col md:flex-wrap md:flex-row gap-6">
            {cards
              ? cards.map((card) => (
                  <div
                    key={card}
                    className="w-full md:max-w-[300px] md:min-w-[300px] h-80 mx-auto bg-blue-300 flex items-center justify-center"
                  >
                    <p>{card}</p>
                  </div>
                ))
              : null}
          </div>
        </div>
      </div>
    </div>
  );
};
export default Home;
