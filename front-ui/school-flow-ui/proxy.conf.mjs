export default [
  {
    context: [
      '/STUDENT-SERVICE',
      '/CLASS-SERVICE',
      '/USER-SERVICE',
      '/PROPERTY-SERVICE',
      '/api',
      '/oauth2',
      '/login'
    ],
    target: 'http://localhost:8800',
    secure: true
  }
]
