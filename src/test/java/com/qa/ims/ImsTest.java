package com.qa.ims;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.action.EntityAction;
import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.domain.Domain;

@RunWith(MockitoJUnitRunner.class)
public class ImsTest {
	
	@Mock
	private ItemController itemController;
	
	@Spy
	private Ims ims;
	
	@Test
	public void imsSystemTest() {
		String login = "root";
		Mockito.doReturn(login, login).when(ims).getInput();
		Mockito.doReturn(Domain.STOP).when(ims).getDomain();
		
		ims.imsSystem();
		Mockito.verify(ims, Mockito.times(1)).init(login, login);
	}
	
	@Test
	public void entityMenuTest() {
		String login = "root";
		Mockito.doReturn(Domain.CUSTOMER, Domain.STOP).when(ims).getDomain();
		Mockito.doReturn(EntityAction.READ).when(ims).getAction();
		
		assertTrue(ims.entityMenu(login, login));
	}
	
	@Test
	public void doActionTest() {
		ims.doAction(itemController, EntityAction.UPDATE);
		Mockito.verify(itemController, Mockito.times(1)).update();
	}
	
	@Test
	public void readFileTest() {
		String fileLocation = "src/test/resources/read-test.txt";
		assertEquals("test text\r\nhello world\r\n", ims.readFile(fileLocation));
	}
	
//	@Test
//	public void initTest() {
//		
//	}
}