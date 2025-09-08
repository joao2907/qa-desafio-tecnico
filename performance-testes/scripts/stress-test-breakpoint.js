import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 100 },    // aquece com 100 usuários
    { duration: '30s', target: 500 },    // sobe para 500
    { duration: '30s', target: 1000 },   // sobe para 1000
    { duration: '30s', target: 2000 },   // dobra para 2000
    { duration: '30s', target: 3000 },   // mais carga
    { duration: '30s', target: 5000 },   // carga máxima
    { duration: '1m', target: 0 },       // ramp-down
  ],

  thresholds: {
    http_req_duration: ['p(95)<5000'], // critério de sucesso
    http_req_failed: ['rate<0.10'],    // erro < 10%
  },
};

export default function () {
  const res = http.get('https://jsonplaceholder.typicode.com/posts');

  check(res, {
    'status é 200': (r) => r.status === 200,
    'tempo de resposta < 5s': (r) => r.timings.duration < 5000,
  });

  sleep(1);
}
