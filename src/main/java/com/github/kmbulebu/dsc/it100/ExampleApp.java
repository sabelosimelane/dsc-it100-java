package com.github.kmbulebu.dsc.it100;

import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneAlarmCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneAlarmRestoreCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneOpenCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneRestoredCommand;
import com.github.kmbulebu.dsc.it100.commands.write.WriteCommand;

import rx.Observable;
import rx.functions.Action1;

public class ExampleApp {

	public static void main(String[] args) {
		
		// Configure for Envisalink (defaults)
		final IT100 it100 = new IT100(new ConfigurationBuilder()
				.withRemoteSocket("192.168.1.134", 4025)
				.withStatusPolling(15)
				.withEnvisalinkPassword("user").build());
		
		try {	
			// Start communicating with IT-100.
			it100.connect();
			
			final Observable<ReadCommand> readObservable = it100.getReadObservable();
			
			// Labels gives us friendly names to our zones.
			final Labels labels = new Labels(readObservable, it100.getWriteObservable());
			
			it100.getWriteObservable().subscribe(new Action1<WriteCommand>() {

				@Override
				public void call(WriteCommand command) {
					System.out.println("Write: " + System.currentTimeMillis() + " " + command.getCommandCode() + " " + command.getData());
				}
				
			});
			
			// Subscribe to all
			readObservable.subscribe(new Action1<ReadCommand>() {

				@Override
				public void call(ReadCommand command) {
					// TODO Auto-generated method stub
					System.out.println("Read: " + System.currentTimeMillis() + " " + command.getCommandCode() + " " + command.toString());
				}
				
			});
			
			// Subscribe to Zone opening events to print zone labels (Only on IT-100)
			readObservable.ofType(ZoneOpenCommand.class).subscribe(new Action1<ZoneOpenCommand>() {

				@Override
				public void call(ZoneOpenCommand t1) {
					// Print time and name of zone that opened.
					System.out.println("ZoneOpen: "+System.currentTimeMillis() + " " + t1.getZone() + " opened.");
				}
				
			});
			
			// Subscribe to Zone closing events to print zone labels (Only on IT-100)
			readObservable.ofType(ZoneRestoredCommand.class).subscribe(new Action1<ZoneRestoredCommand>() {

				@Override
				public void call(ZoneRestoredCommand t1) {
					System.out.println("ZoneOpen: "+System.currentTimeMillis() + " " + t1.getZone() + " closed.");
				}
				
			});
			
			readObservable.ofType(ZoneAlarmCommand.class).subscribe(new Action1<ZoneAlarmCommand>() {

				@Override
				public void call(ZoneAlarmCommand t1) {
					System.out.println("ZoneAlarm: "+System.currentTimeMillis() + " " + t1.getZone() + " closed.");
				}
				
			});
			
			readObservable.ofType(ZoneAlarmRestoreCommand.class).subscribe(new Action1<ZoneAlarmRestoreCommand>() {

				@Override
				public void call(ZoneAlarmRestoreCommand t1) {
					System.out.println("ZoneAlarm: "+System.currentTimeMillis() + " " + t1.getZone() + " closed.");
				}
				
			});
			
			//Thread.sleep(60000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
