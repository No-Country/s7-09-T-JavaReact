import AppRouter from "./routes/AppRouter";
import { QueryClient, QueryClientProvider } from "react-query";

const client = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={client}>
      <AppRouter />
    </QueryClientProvider>
  );
}

export default App;
