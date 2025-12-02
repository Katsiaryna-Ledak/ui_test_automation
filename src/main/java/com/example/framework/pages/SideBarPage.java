package com.example.framework.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class SideBarPage {
    private SelenideElement getParentElement(SideBarParentItem parent) {
        String xpath = "//ul//li//span[normalize-space()='" + parent.getTitle() + "']";
        return $x(xpath);
    }

    private SelenideElement getChildElement(SideBarChildItem child) {
        String xpath = "//ul//li//span[normalize-space()='" + child.getParent().getTitle() + "']" +
                "/following-sibling::ul//span[normalize-space()='" + child.getTitle() + "']";
        return $x(xpath);
    }

    public void openParent(SideBarParentItem parent) {
        log.info("Open parent menu item: {}", parent.getTitle());

        SelenideElement element = getParentElement(parent);
        scrollSidebarToElement(element);
        element.shouldBe(visible, Duration.ofSeconds(5)).click();
        sleep(300);
    }

    public void closeParent(SideBarParentItem parent) {
        log.info("Close parent menu item: {}", parent.getTitle());

        SelenideElement element = getParentElement(parent);
        scrollSidebarToElement(element);
        element.shouldBe(visible, Duration.ofSeconds(5)).click();
        sleep(300);
    }

    public void clickChild(SideBarChildItem child) {
        log.info("Click child menu item: {} under parent {}", child.getTitle(), child.getParent().getTitle());

        SelenideElement element = getChildElement(child);
        scrollSidebarToElement(element);
        element.shouldBe(visible, Duration.ofSeconds(5)).click();
        sleep(300);
    }

    private void scrollSidebarToElement(SelenideElement element) {
        executeJavaScript(
                "const sidebar = document.querySelector('[role=\"region\"]');" +
                        "if (sidebar && arguments[0]) {" +
                        "  sidebar.scrollTop = arguments[0].offsetTop - sidebar.offsetHeight / 2;" +
                        "}", element
        );
        executeJavaScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'})", element);
    }

    @Getter
    public enum SideBarParentItem {
        DEDICATED_SERVERS("Dedicated Servers"),
        CLOUD_SERVERS("Cloud Servers"),
        CLOUD_STORAGE("Cloud Storage"),
        KUBERNETES("Kubernetes"),
        LOAD_BALANCERS("Load Balancers"),
        FIREWALLS("Firewalls"),
        NETWORKS("Networks"),
        PRIVATE_RACKS("Private Racks"),
        MONITORING("Monitoring"),
        SSL_CERTIFICATES("SSL certificates"),
        ACCOUNT_SETTINGS("Account settings"),
        IDENTIFY_AND_ACCESS("Identity and Access"),
        BILLING("Billing"),
        REPORTS("Reports"),
        REQUESTS("Requests");

        private final String title;

        SideBarParentItem(String title) {
            this.title = title;
        }
    }

    @Getter
    public enum SideBarChildItem {
        MANAGE("Manage", SideBarParentItem.DEDICATED_SERVERS, "/servers/my"),
        ORDER("Order", SideBarParentItem.DEDICATED_SERVERS, "/servers/order"),
        CREATE_AND_MANAGE("Create & Manage", SideBarParentItem.CLOUD_SERVERS, "/cloud-computing?page=1"),
        SNAPSHOTS_AND_BACKUPS("Snapshots & Backups", SideBarParentItem.CLOUD_SERVERS, "/cloud-computing/snapshots"),
        IMAGES("Images", SideBarParentItem.CLOUD_SERVERS, "/cloud-computing/images"),
        VOLUMES("Volumes", SideBarParentItem.CLOUD_SERVERS, "/cloud-computing/block-storage/info"),
        DIRECT_CONNECT("Direct Connect", SideBarParentItem.NETWORKS, "/networks/dc/welcome"),
        L2_SEGMENTS("L2 Segments", SideBarParentItem.NETWORKS, "/networks/l2"),
        DNS("DNS", SideBarParentItem.NETWORKS, "/networks/dns"),
        VPN_ACCESS("VPN access", SideBarParentItem.NETWORKS, "/networks/vpn"),
        HEALTHCHECKS("Healthchecks", SideBarParentItem.MONITORING, "/monitoring/healthchecks"),
        NOTIFICATIONS("Notifications", SideBarParentItem.MONITORING, "/monitoring/notifications"),
        SSH_AND_GPG("SSH & GPG keys", SideBarParentItem.IDENTIFY_AND_ACCESS, "/iam/keys"),
        API_TOKENS("API tokens", SideBarParentItem.IDENTIFY_AND_ACCESS, "/iam/api-tokens"),
        ORDERS("Orders", SideBarParentItem.BILLING, "/billing/orders"),
        INVOICES("Invoices", SideBarParentItem.BILLING, "/billing/invoices"),
        GROUP_INVOICES("Group invoices", SideBarParentItem.BILLING, "/billing/group-invoices"),
        ACCOUNT_STATEMENT("Account statement", SideBarParentItem.BILLING, "/billing/statement"),
        PAYMENT_DETAILS("Payment details", SideBarParentItem.BILLING, "/payment/methods"),
        TOP_UP_BALANCE("Top up balance", SideBarParentItem.BILLING, "/payment/pay"),
        CLOUD_SERVERS("Cloud Servers", SideBarParentItem.REPORTS, "/cloud-computing/reports"),
        CLOUD_STORAGE("Cloud Storage", SideBarParentItem.REPORTS, "/cloud-storage/reports");

        private final String title;
        private final SideBarParentItem parent;
        private final String url;

        SideBarChildItem(String title, SideBarParentItem parent, String url) {
            this.title = title;
            this.parent = parent;
            this.url = url;
        }
    }
}
