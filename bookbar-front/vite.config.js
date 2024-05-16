import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  resolve: {
    extensions: ['.js', '.jsx'], // Ensure these extensions are resolved
  },
  esbuild: {
    jsxFactory: 'React.createElement',
    jsxFragment: 'React.Fragment',
    loader: 'jsx', // Use the 'jsx' loader for JSX files
    include: /src\/.*\.jsx?$/, // Include .jsx and .js files in the src folder
  },
  server: {
    port: 3000,
    proxy: {
      '/api': 'http://localhost:8080', // Proxy API requests to Spring Boot backend
    },
  },
});
