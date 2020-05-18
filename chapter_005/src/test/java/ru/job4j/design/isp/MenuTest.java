package ru.job4j.design.isp;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MenuTest {
    @Test
    public void testSingleItemMenu() {
        MenuItem item = new MenuItem("single");
        assertThat(item.getName(), is("single"));
    }

    @Test
    public void testAddSubItem() {
        MenuItem menu = new MenuItem("root");
        MenuItem subMenu = new MenuItem("submenu");
        menu.addItem(subMenu);
        assertThat(menu.getSubItems().size(), is(1));
        assertThat(menu.getSubItems().get(0), is(subMenu));
    }

    @Test
    public void testAddThreeSubItems() {
        MenuItem menu = new MenuItem("root");
        MenuItem subMenu1 = new MenuItem("submenu1");
        MenuItem subMenu2 = new MenuItem("submenu2");
        MenuItem subMenu3 = new MenuItem("submenu3");
        menu.addItem(subMenu1);
        menu.addItem(subMenu2);
        menu.addItem(subMenu3);
        assertThat(menu.getSubItems(), is(List.of(subMenu1, subMenu2, subMenu3)));
    }

    @Test
    public void testAddMultiLevelsSubItems() {
        MenuItem menu = new MenuItem("root");
        MenuItem subMenu1 = new MenuItem("submenu1");
        MenuItem subMenu2 = new MenuItem("submenu2");
        MenuItem subMenu3 = new MenuItem("submenu3");
        MenuItem subSubItem1 = new MenuItem("subsubitem1");
        MenuItem subSubItem2 = new MenuItem("subsubitem1");
        MenuItem subSubItem3 = new MenuItem("subsubitem1");
        subMenu2.addItem(subSubItem1);
        subMenu2.addItem(subSubItem2);
        subMenu2.addItem(subSubItem3);
        menu.addItem(subMenu1);
        menu.addItem(subMenu2);
        menu.addItem(subMenu3);
        assertThat(menu.getSubItems(), is(List.of(subMenu1, subMenu2, subMenu3)));
        assertThat(menu.getSubItems().get(1).getSubItems(), is(List.of(subSubItem1, subSubItem2, subSubItem3)));
    }

    @Test
    public void testActionWasCalled() {
        Action action = mock(MenuAction.class);
        MenuItem menu = new MenuItem("root");
        menu.setAction(action);
        menu.doAction();
        verify(action).doAction();
    }

    @Test
    public void printMenu() {
        MenuItem menu = new MenuItem("root");
        MenuItem subMenu1 = new MenuItem("submenu1");
        MenuItem subMenu2 = new MenuItem("submenu2");
        MenuItem subMenu3 = new MenuItem("submenu3");
        MenuItem subSubItem1 = new MenuItem("subsubitem1");
        MenuItem subSubItem2 = new MenuItem("subsubitem2");
        MenuItem subSubItem3 = new MenuItem("subsubitem3");
        subMenu2.addItem(subSubItem1);
        subMenu2.addItem(subSubItem2);
        subMenu2.addItem(subSubItem3);
        menu.addItem(subMenu1);
        menu.addItem(subMenu2);
        menu.addItem(subMenu3);
        MenuPrinter.printMenu(menu);
    }


}