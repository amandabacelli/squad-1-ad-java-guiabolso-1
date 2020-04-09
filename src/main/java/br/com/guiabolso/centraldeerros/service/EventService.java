package br.com.guiabolso.centraldeerros.service;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.mapper.EventMapper;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	@Autowired
    EventRepository eventRepository;


	public Page<Event> findAll(Specification<Event> spec, Pageable pageable) {
		return eventRepository.findAll(spec, pageable);
	}


	public Event save(Event event) {
		return eventRepository.save(event);
	}


	public EventDTO findById(Long id) {
		return EventMapper.toEventDTO(eventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Evento não encontrado",Event.class.getName())));
	}

	public void update(EventDTO eventDTO, Long id){
		eventDTO.setId(id);
		Event event = EventMapper.toEvent(eventDTO);
		eventRepository.save(event);
	}





}
