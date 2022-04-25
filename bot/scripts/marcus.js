"use strict";

const getOrCreateEmloyeeUrl = "http://rest-service:8080/getOrCreateEmployee";

module.exports = (robot) => {

  robot.respond(/(.*)/i, (res) => {
    let userId = res.message.user.id; // here it's believed that all these fields are mandatory and not nulls    
    if (robot.brain.get(userId)) {
      console.log(`User's authentication was retrieved from cache by id: ${userId}`);
      return;
    } 
    let payload = JSON.stringify({
      id: userId,
      name: res.message.user.name
    });
    new Promise((resolve, reject) =>
      robot.http(getOrCreateEmloyeeUrl)
        .header('Content-Type', 'application/json')
        .put(payload)((err, response, body) =>
          err ? reject(err) : resolve(body)
        )
    ).then(body => {
      let employee = JSON.parse(body);
      res.reply(`Hi! You was successfully authorized with ID : ${employee.id}`);
      robot.brain.set(employee.id, employee); // this should be ammended by some useful data
    })
      .catch(err => res.reply(`Error has been encountered: ${err}`))
  });

}