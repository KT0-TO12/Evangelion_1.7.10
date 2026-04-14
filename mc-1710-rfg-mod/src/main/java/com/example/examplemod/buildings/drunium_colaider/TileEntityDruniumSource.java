package com.example.examplemod.buildings.drunium_colaider;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDruniumSource extends TileEntity {
    private int currentDrunentum = 0;
    private int tickCounter = 0;

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;

        tickCounter++;
        if (tickCounter >= 20) { // Каждую секунду проверяем продвижение частицы
            processStep();
            tickCounter = 0;
        }
    }

    private void processStep() {
        // Начинаем цикл с 100, если частица только создана
        if (currentDrunentum == 0) currentDrunentum = 100;

        // Ищем следующий блок в цепи (например, по направлению выхода)
        ForgeDirection dir = ForgeDirection.NORTH; // Условно
        TileEntity nextTE = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);

        if (nextTE instanceof TileEntityAmplifier) {
            currentDrunentum += 100;
            // Передаем данные дальше...
            sendVisualSpark(dir);
        }

        else if (nextTE instanceof TileEntitySensor) {
            if (currentDrunentum >= 1500) {
                // ДАТЧИК ОТКРЫТ: Сбрасываем заряд в приемник
                sendToReceiver();
                currentDrunentum = 0;
            } else {
                // ИДЕМ НА КРУГ: Продолжаем цикл
                continueCircle();
            }
        }
    }

    private void sendVisualSpark(ForgeDirection dir) {
        // Команда клиенту отрисовать частицу в трубе для красоты
    }
}
