package com.github.relativobr.supreme.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class SupremePowerSection {

  int capacitorAurumCapacity;
  int capacitorTitaniumCapacity;
  int capacitorAdamantiumCapacity;
  int capacitorThorniumCapacity;
  int capacitorSupremeCapacity;
  int generatorBasicIgnisEnergy;
  int generatorBasicIgnisBuffer;
  int generatorBasicVentusEnergy;
  int generatorBasicVentusBuffer;
  int generatorBasicAquaEnergy;
  int generatorBasicAquaBuffer;
  int generatorBasicLuxEnergy;
  int generatorBasicLuxBuffer;
  int generatorBasicLumiumEnergy;
  int generatorBasicLumiumBuffer;
  int generatorIgnisEnergy;
  int generatorIgnisBuffer;
  int generatorVentusEnergy;
  int generatorVentusBuffer;
  int generatorAquaEnergy;
  int generatorAquaBuffer;
  int generatorLuxEnergy;
  int generatorLuxBuffer;
  int generatorLumiumEnergy;
  int generatorLumiumBuffer;
  int generatorThorniumEnergy;
  int generatorThorniumBuffer;
  int generatorSupremeEnergy;
  int generatorSupremeBuffer;

  public static SupremePowerSection defaultValue() {
    return SupremePowerSection.builder()
            .capacitorAurumCapacity(250000)
            .capacitorTitaniumCapacity(1000000)
            .capacitorAdamantiumCapacity(16000)
            .capacitorThorniumCapacity(1000000)
            .capacitorSupremeCapacity(16000000)

            .generatorBasicIgnisEnergy(50)
            .generatorBasicIgnisBuffer(5000)

            .generatorBasicVentusEnergy(50)
            .generatorBasicVentusBuffer(5000)

            .generatorBasicAquaEnergy(50)
            .generatorBasicAquaBuffer(5000)

            .generatorBasicLuxEnergy(50)
            .generatorBasicLuxBuffer(5000)

            .generatorBasicLumiumEnergy(50)
            .generatorBasicLumiumBuffer(10000)

            .generatorIgnisEnergy(1000)
            .generatorIgnisBuffer(50000)

            .generatorVentusEnergy(1000)
            .generatorVentusBuffer(50000)

            .generatorAquaEnergy(1000)
            .generatorAquaBuffer(50000)

            .generatorLuxEnergy(1000)
            .generatorLuxBuffer(50000)

            .generatorLumiumEnergy(1000)
            .generatorLumiumBuffer(500000)


            .generatorThorniumEnergy(300000)
            .generatorThorniumBuffer(6000000)

            .generatorSupremeEnergy(50000)
            .generatorSupremeBuffer(12000000)
            .build();
  }
}
