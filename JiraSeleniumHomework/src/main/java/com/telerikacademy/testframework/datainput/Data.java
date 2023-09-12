package com.telerikacademy.testframework.datainput;

public class Data {

    public static String summaryStory = "As a desktop user of https://www.trip.com, " +
            "I want an enhanced navigation menu to easily explore and" +
            " access the website's features and travel options.";
    public static String descriptionStory = "Currently, the navigation menu could be improved to " +
            "provide a better user experience for desktop users. " +
            "The existing menu layout is not intuitive and can be cumbersome to " +
            "navigate, especially when looking for specific travel options or services. " +
            "To address this, we need to revamp the desktop navigation menu to " +
            "make it more user-friendly, organized, and efficient.\n";


    public static String summaryBug = "Desktop Navigation Menu is Unresponsive";
    public static String descriptionBug =
            "The desktop navigation menu on https://www.trip.com is unresponsive, " +
                    "rendering the website virtually unusable for desktop users. " +
                    "When attempting to interact with the navigation menu, no dropdown " +
                    "or flyout menus appear, and clicking on menu items does not lead to " +
                    "the corresponding pages or sections.\n" +
                    "Steps to Reproduce:\n" +
                    "1. Navigate to https://www.trip.com/.\n" +
                    "Attempt to use the desktop navigation menu by hovering over or clicking on various menu items.\n" +
                    "\n" +
                    "Expected Results:\n" +
                    "The desktop navigation menu should be responsive, displaying dropdown or " +
                    "flyout menus upon interaction, and clicking on menu items should " +
                    "navigate users to the relevant pages or sections.\n" +
                    "Actual Results:\n" +
                    "The desktop navigation menu is entirely unresponsive. " +
                    "Hovering over menu items does not trigger the display of submenus, " +
                    "and clicking on any menu item does not result in any action. " +
                    "The menu remains static, and users cannot access other pages or " +
                    "sections through the navigation.\n";
}
