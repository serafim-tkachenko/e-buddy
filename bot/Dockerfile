FROM node:16.4.2

RUN npm install -g coffee-script yo generator-hubot && useradd hubot -m

USER hubot

WORKDIR /home/hubot

ENV BOT_NAME "Marcus"
ENV BOT_OWNER "No owner specified"
ENV BOT_DESC "A smart bot that guides through some HR-related topics and more"
ENV HUBOT_LOG_LEVEL "error"

ENV EXTERNAL_SCRIPTS=hubot-diagnostics,hubot-help,hubot-google-images,hubot-google-translate,hubot-pugme,hubot-maps,hubot-rules,hubot-shipit

RUN yo hubot --owner="$BOT_OWNER" --name="$BOT_NAME" --description="$BOT_DESC" --defaults && \
	sed -i /heroku/d ./external-scripts.json && \
	sed -i /redis-brain/d ./external-scripts.json && \
	npm install hubot-scripts

ADD . /home/hubot/node_modules/hubot-rocketchat

# hack added to get around owner issue: https://github.com/docker/docker/issues/6119
USER root
RUN chown hubot:hubot -R /home/hubot/node_modules/hubot-rocketchat
USER hubot

RUN cd /home/hubot/node_modules/hubot-rocketchat && \
	npm install && \
	cd /home/hubot

CMD node -e "console.log(JSON.stringify('$EXTERNAL_SCRIPTS'.split(',')))" > external-scripts.json && \
	npm install $(node -e "console.log('$EXTERNAL_SCRIPTS'.split(',').join(' '))") && \
	bin/hubot -n $BOT_NAME -a rocketchat