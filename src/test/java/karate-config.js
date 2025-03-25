function fn() {
  var env = karate.env;
  karate.log('karate.env is:', env);
  if (!env) {
    env = 'dev';
  }
  var config = {
    env: env,
    apiUrl: 'https://dev.example.com'
  }
  if (env == 'qa') {
    config.apiUrl = 'https://qa.example.com';
  } else if (env == 'prod') {
    config.apiUrl = 'https://prod.example.com';
  }
  return config;
}
