package com.approachingpi.services.jobs;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

import com.wirelust.personalapi.services.Configuration;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;

/**
 * Date: 20-Dec-2015
 *
 * @author T. Curran
 */
@Singleton
@Startup
public class FitbitSyncJob {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FitbitSyncJob.class);

	@Resource
	TimerService timerService;

	@Inject
	Configuration configuration;

	Timer timer;

	@PostConstruct
	public void init() {
		if (!configuration.isFitbitSync()) {
			LOGGER.info("Fitbit sync disabled");
			return;
		}

		String fitbitSchedule = configuration.getFitbitSchedule();
		LOGGER.info("configuring fitbit sync job. schedule:{}", fitbitSchedule);

		// set initial timer
		ScheduleExpression scheduleExpression = new ScheduleExpression();

		String[] cronArray = fitbitSchedule.split(" ");
		scheduleExpression.second(cronArray[0]);
		scheduleExpression.minute(cronArray[1]);
		scheduleExpression.hour(cronArray[2]);
		scheduleExpression.dayOfMonth(cronArray[3]);
		scheduleExpression.month(cronArray[4]);
		scheduleExpression.dayOfWeek(cronArray[5]);
		scheduleExpression.year(cronArray[6]);

		timer = timerService.createCalendarTimer(scheduleExpression, new TimerConfig("FitBit", false));
	}

	@Timeout
	public void timeout(Timer timer) {
		LOGGER.info("Fitbit sync timer timeout");
	}
}
