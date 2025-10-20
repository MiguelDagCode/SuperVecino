package com.dagache.supervecino.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dagache.supervecino.data.repository.UsuarioRepository
import com.dagache.supervecino.data.room.database.AppDatabase
import com.dagache.supervecino.databinding.FragmentLoginBinding
import com.dagache.supervecino.viewmodel.UsuarioViewModel
import com.dagache.supervecino.viewmodel.UsuarioViewModelFactory

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dao = AppDatabase.Companion.getDatabase(requireContext()).usuarioDAO()
        val repo = UsuarioRepository(dao)
        val factory = UsuarioViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory)[UsuarioViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val correo = binding.etCorreo.text.toString()
            val contrasena = binding.etContrasena.text.toString()

            if (correo.isNotEmpty() && contrasena.isNotEmpty()) {
                viewModel.login(correo, contrasena)
            } else {
                Toast.makeText(requireContext(), "Campos vacíos", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess { user ->
                Toast.makeText(requireContext(), "Bienvenido ${user.nombre}", Toast.LENGTH_SHORT).show()
                // Navegar a pantalla principal(intent)
            }

            result.onFailure {
                Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}