import http from 'k6/http';
import { check, sleep } from 'k6';

// Configuração de carga
export const options = {
  stages: [
    { duration: '30s', target: 10 },   // sobe para 10 usuários
    { duration: '30s', target: 100 },  // sobe para 100 usuários
    { duration: '30s', target: 500 },  // sobe para 500 usuários
    { duration: '30s', target: 1000 }, // sobe para 1000 usuários
    { duration: '1m', target: 0 },     // rampa para baixo (teardown)
  ],

  thresholds: {
    http_req_duration: ['p(95)<5000'], // 95% das req devem responder em < 5s
    http_req_failed: ['rate<0.10'],    // taxa de erro deve ser < 10%
  },
};

// Teste em si
export default function () {
  const res = http.get('https://jsonplaceholder.typicode.com/posts');

  check(res, {
    'status é 200': (r) => r.status === 200,
    'tempo de resposta < 5s': (r) => r.timings.duration < 5000,
  });

  sleep(1); // pensa 1s antes da próxima iteração
}
