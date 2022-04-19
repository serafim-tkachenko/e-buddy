const hiUrl = "http://rest-service:8080/hi";

module.exports = (robot) => {
  robot.respond(/(hello|hi)/gi, (res) => {
    console.log('Message was successfully processed');
    new Promise((resolve, reject) =>
      robot.http(hiUrl).get()((err, response, body) =>
        err ? reject(err) : resolve(body)
      )
    ).then(body => {console.log('Inside the promise', body); res.reply(`The received message is: ${body}`); return;})
    .catch(err => res.reply(`Error has been encountered: ${err}`))
  })

}