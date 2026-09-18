package com.example.projectoSupermercado.service;

import com.example.projectoSupermercado.dto.SucursalDTO;
import com.example.projectoSupermercado.exception.NotFoundException;
import com.example.projectoSupermercado.mapper.Mapper;
import com.example.projectoSupermercado.model.Sucursal;
import com.example.projectoSupermercado.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalService implements  ISucursalService{

    @Autowired
    SucursalRepository sucuRepo;

    @Override
    public List<SucursalDTO> traerSucursales() {

        sucuRepo.findAll()
                .stream()
                .map(Mapper::sToDTO)
                .toList();




        return List.of();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDto) {

        Sucursal sucu = Sucursal.builder()
                .id(sucursalDto.getId())
                .nombre(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();

        return Mapper.sToDTO(sucuRepo.save(sucu));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto) {


        Sucursal sucu = sucuRepo.findById(id).orElseThrow(()-> new NotFoundException
                ("la sucursal no existe o no fue encontrada mediante la id suministrada"));

        sucu.setNombre(sucursalDto.getNombre());
        sucu.setDireccion(sucursalDto.getDireccion());

        return Mapper.sToDTO(sucuRepo.save(sucu));


    }

    @Override
    public void eliminarSucursal(Long id) {

        if(!sucuRepo.existsById(id)){
            throw new NotFoundException("la id no fue encotrada");

        }

        sucuRepo.deleteById(id);

    }
}
