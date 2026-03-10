package org.itmo.testing.lab2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserStatusServiceTest {

    private UserAnalyticsService userAnalyticsService;
    private UserStatusService userStatusService;

    @BeforeEach
    void setUp() {
        userAnalyticsService = mock(UserAnalyticsService.class);
        userStatusService = new UserStatusService(userAnalyticsService);
    }

    @Test
    @DisplayName("getUserStatus возвращает 'Active' при активности 60-119 минут")
    void testGetUserStatus_Active() {
        // Настроим поведение mock-объекта
        when(userAnalyticsService.getTotalActivityTime("user123")).thenReturn(90L);

        String status = userStatusService.getUserStatus("user123");

        assertEquals("Active", status);
        // Проверяем, что метод был вызван ровно 1 раз с правильным аргументом
        verify(userAnalyticsService).getTotalActivityTime("user123");
    }
}
